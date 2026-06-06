package com.ruoyi.iot.config;

import com.ruoyi.iot.processor.IotDataProcessor;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private static final Logger log = LoggerFactory.getLogger(MqttConfig.class);

    @Value("${iot.mqtt.url}")
    private String hostUrl;

    @Value("${iot.mqtt.clientId}")
    private String clientId;

    @Value("${iot.mqtt.username:admin}")
    private String username;

    @Value("${iot.mqtt.password:public}")
    private String password;

    @Value("${iot.mqtt.defaultTopic}")
    private String defaultTopic;

    @Bean
    public MqttClient mqttClient() {
        // 澹版槑涓?final锛岀‘淇濆尶鍚嶅唴閮ㄧ被涓彲浠ュ畨鍏ㄧǔ瀹氬湴寮曠敤褰撳墠姝ｅ湪鍒涘缓鐨勮繖涓璞?
        final MqttClient client;
        try {
            // 1. 鍒濆鍖栧鎴风
            client = new MqttClient(hostUrl, clientId);

            // 2. 閰嶇疆杩炴帴璁よ瘉鍙傛暟
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setAutomaticReconnect(true); // 鍚敤鏂嚎鑷姩閲嶈繛
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(10);    // 瓒呮椂鏃堕棿
            options.setKeepAliveInterval(20);    // 蹇冭烦闂撮殧

            // 3. 閰嶇疆娑堟伅鍒拌揪鍚庣殑鍥炶皟鐩戝惉
            client.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    log.info("馃煝 [IoT] 鎴愬姛杩炴帴鑷?EMQX 娑堟伅鏈嶅姟鍣?({})", serverURI);
                    try {
                        // 馃専 淇锛氱洿鎺ョ敤褰撳墠鍒濆鍖栫殑 client 瀹炰緥杩涜璁㈤槄锛屾嫆缁濇寰幆璋冪敤锛?
                        client.subscribe(defaultTopic, 1);
                        log.info("馃洶锔?[IoT] 宸叉垚鍔熻闃呯墿鑱旂綉涓撳睘涓婚: {}", defaultTopic);
                    } catch (MqttException e) {
                        log.error("鉂?[IoT] 璁㈤槄涓婚澶辫触: {}", e.getMessage());
                    }
                }

                @Override
                public void connectionLost(Throwable cause) {
                    log.warn("馃敶 [IoT] 涓?EMQX 鏂紑杩炴帴锛岀郴缁熸鍦ㄨ嚜鍔ㄥ皾璇曢噸杩?..");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    String payload = new String(message.getPayload());
                    log.info("馃摜 [IoT] 鏀跺埌涓婃姤鏁版嵁 -> 涓婚: [{}], 瀛楄妭澶у皬: {}", topic, message.getPayload().length);
                    IotDataProcessor.handleMessage(topic, payload);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });

            // 4. 鍚屾鍙戣捣杩炴帴
            client.connect(options);
            return client;

        } catch (MqttException e) {
            log.error("馃挜 [IoT] 鍒濆鍖?MQTT 瀹㈡埛绔椂鍙戠敓涓ラ噸寮傚父: ", e);
        }
        return null;
    }
}
