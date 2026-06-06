package com.ruoyi.iot.processor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IotDataProcessor {

    private static final Logger log = LoggerFactory.getLogger(IotDataProcessor.class);

    /**
     * 瑙ｆ瀽骞跺鐞嗕粠 EMQX 杞彂杩囨潵鐨勮澶囨暟鎹?
     *
     * @param topic   璁惧涓婃姤鐨勪富棰橈紝褰㈠: new_agriculture/DEV-001/telemetry
     * @param payload 璁惧涓婃姤鐨勫師濮嬫枃鏈暟鎹紝閫氬父鏄?JSON 瀛楃涓?
     */
    public static void handleMessage(String topic, String payload) {
        try {
            // 1. 浠庝富棰樿矾寰勪腑瑙ｆ瀽鍑哄姩鎬佺殑璁惧缂栫爜锛圖evice ID锛?
            String[] split = topic.split("/");
            if (split.length < 3) {
                log.warn("鈿狅笍 [IoT] 鏀跺埌寮傚父鏍煎紡鐨勪富棰橈紝鎷掔粷瑙ｆ瀽: {}", topic);
                return;
            }
            String deviceSn = split[1]; // 鑾峰彇 + 浣嶇疆瀵瑰簲鐨勭湡瀹炶澶囧彿

            // 2. 浣跨敤鏂扮増鑻ヤ緷鑷甫鐨?fastjson2 瀹夊叏鍦拌В鏋愭姤鏂?
            if (!JSON.isValid(payload)) {
                log.warn("鈿狅笍 [IoT] 璁惧 [{}] 涓婃姤鐨勯潪鏍囧噯 JSON 鎶ユ枃: {}", deviceSn, payload);
                return;
            }
            JSONObject dataJson = JSON.parseObject(payload);

            log.info("馃挕 [IoT] 鏁版嵁瑙ｆ瀽鎴愬姛 -> 璁惧: [{}], 鎶ユ枃璇︽儏: {}", deviceSn, dataJson.toJSONString());

            // 馃洜锔?涓嬩竴姝ユ嫇灞曟柟鍚戯細
            // Double temperature = dataJson.getDouble("temperature"); // 鎻愬彇娓╁害
            // Double humidity = dataJson.getDouble("humidity");       // 鎻愬彇婀垮害

            // TODO: 閫氳繃 Spring 瀹瑰櫒鎷垮埌浣犵殑 Service锛岀洿鎺ュ瓨鍏ユ湰鍦版暟鎹簱
            // SpringUtils.getBean(IIotDeviceDataService.class).saveData(deviceSn, temperature, humidity);

        } catch (Exception e) {
            log.error("鉂?[IoT] 澶勭悊璁惧鍘熷鎶ユ枃鏃跺彂鐢熺郴缁熷紓甯? ", e);
        }
    }
}
