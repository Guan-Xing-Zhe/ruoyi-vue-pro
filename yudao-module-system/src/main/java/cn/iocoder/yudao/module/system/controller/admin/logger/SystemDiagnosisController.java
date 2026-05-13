package cn.iocoder.yudao.module.system.controller.admin.logger;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "管理后台 - 系统诊断")
@RestController
@RequestMapping("/system/diagnosis")
public class SystemDiagnosisController {

    @GetMapping("/jvm-info")
    @Operation(summary = "获取JVM运行时信息")
    @PreAuthorize("@ss.hasPermission('system:monitor:query')")
    public CommonResult<Map<String, Object>> getJvmInfo() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();

        Map<String, Object> info = new HashMap<>();
        info.put("jvmName", runtime.getVmName());
        info.put("jvmVersion", runtime.getVmVersion());
        info.put("jvmVendor", runtime.getVmVendor());
        info.put("startTime", Instant.ofEpochMilli(runtime.getStartTime()).toString());
        info.put("uptime", formatDuration(runtime.getUptime()));
        info.put("inputArgs", runtime.getInputArguments());

        info.put("osName", os.getName());
        info.put("osArch", os.getArch());
        info.put("osVersion", os.getVersion());
        info.put("availableProcessors", os.getAvailableProcessors());

        info.put("heapInit", formatBytes(memory.getHeapMemoryUsage().getInit()));
        info.put("heapUsed", formatBytes(memory.getHeapMemoryUsage().getUsed()));
        info.put("heapMax", formatBytes(memory.getHeapMemoryUsage().getMax()));
        info.put("nonHeapUsed", formatBytes(memory.getNonHeapMemoryUsage().getUsed()));

        return CommonResult.success(info);
    }

    @GetMapping("/thread-overview")
    @Operation(summary = "获取线程概览")
    @PreAuthorize("@ss.hasPermission('system:monitor:query')")
    public CommonResult<Map<String, Object>> getThreadOverview() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        while (group.getParent() != null) {
            group = group.getParent();
        }
        int activeCount = group.activeCount();
        Thread[] threads = new Thread[activeCount * 2];
        int actual = group.enumerate(threads, true);

        Map<String, Object> result = new HashMap<>();
        result.put("activeCount", activeCount);
        result.put("total", actual);

        Map<String, Integer> stateCount = new HashMap<>();
        for (int i = 0; i < actual; i++) {
            String state = threads[i].getState().name();
            stateCount.merge(state, 1, Integer::sum);
        }
        result.put("stateDistribution", stateCount);
        return CommonResult.success(result);
    }

    private String formatDuration(long millis) {
        Duration d = Duration.ofMillis(millis);
        long days = d.toDays();
        long hours = d.toHoursPart();
        long minutes = d.toMinutesPart();
        long seconds = d.toSecondsPart();
        return String.format("%d天 %d小时 %d分 %d秒", days, hours, minutes, seconds);
    }

    private String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024)
            return String.format("%.2f MB", bytes / (1024.0 * 1024));
        return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
    }
}