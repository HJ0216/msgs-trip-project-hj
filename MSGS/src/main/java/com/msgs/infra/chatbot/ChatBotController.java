package com.msgs.infra.chatbot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatBotController {

  @Value("${ChatBot.secretKey}")
  private String secretKey;
  @Value("${ChatBot.apiUrl}")
  private String apiUrl;

  @MessageMapping("/sendMessage")
  @SendTo("/topic/public")
  public String sendMessage(@Payload String chatMessage) throws IOException {

    URL url = new URL(apiUrl);

    String message = getReqMessage(chatMessage);
    System.out.println("==message : " + message);

    String encodeBase64String = makeSignature(message, secretKey);
    System.out.println("==encodeBase64String : " + encodeBase64String);

    //api서버 접속 (서버 -> 서버 통신)
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("Content-Type", "application/json;UTF-8");
    con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());

    wr.write(message.getBytes(StandardCharsets.UTF_8));
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    System.out.println("-------" + responseCode + "-------");

    BufferedReader br;

    if (responseCode == 200) { // 정상 호출

      BufferedReader in = new BufferedReader(
          new InputStreamReader(
              con.getInputStream(), StandardCharsets.UTF_8));
      String decodedString;
      String jsonString = "";
      while ((decodedString = in.readLine()) != null) {
        jsonString = decodedString;
      }

      System.out.println("jsonString: " + jsonString);
      //받아온 값을 세팅하는 부분
      JSONParser jsonparser = new JSONParser();
      try {
        JSONObject json = (JSONObject) jsonparser.parse(jsonString);
        System.out.println(json);

        JSONArray bubblesArray = (JSONArray) json.get("bubbles");
        JSONObject bubbles = (JSONObject) bubblesArray.get(0);
        JSONObject data = (JSONObject) bubbles.get("data");
        String description = "";
        description = (String) data.get("description");
        chatMessage = description;

      } catch (Exception e) {
        System.out.println("error");
        e.printStackTrace();
      }

      in.close();
    } else {  // 에러 발생
      chatMessage = con.getResponseMessage();
    }
    System.out.println("=======chatMessage: " + chatMessage);
    return chatMessage;
  }

  //보낼 메세지를 네이버에서 제공해준 암호화로 변경해주는 메소드
  public static String makeSignature(String message, String secretKey) {

    String encodeBase64String = "";

    try {
      byte[] secrete_key_bytes = secretKey.getBytes(StandardCharsets.UTF_8);

      SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(signingKey);

      byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
      encodeBase64String = Base64.encodeBase64String(rawHmac);

      return encodeBase64String;

    } catch (Exception e) {
      System.out.println(e);
    }

    return encodeBase64String;

  }

  //보낼 메세지를 네이버 챗봇에 포맷으로 변경해주는 메소드
  public static String getReqMessage(String voiceMessage) {

    String requestBody = "";

    try {

      JSONObject obj = new JSONObject();

      long timestamp = new Date().getTime();

      System.out.println("##" + timestamp);

      obj.put("version", "v2");
      obj.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");
      obj.put("timestamp", timestamp);

      JSONObject bubbles_obj = new JSONObject();

      bubbles_obj.put("type", "text");

      JSONObject data_obj = new JSONObject();
      data_obj.put("description", voiceMessage);

      bubbles_obj.put("type", "text");
      bubbles_obj.put("data", data_obj);

      JSONArray bubbles_array = new JSONArray();
      bubbles_array.add(bubbles_obj);

      obj.put("bubbles", bubbles_array);
      obj.put("event", "send");

      requestBody = obj.toString();

    } catch (Exception e) {
      System.out.println("## Exception : " + e);
    }

    return requestBody;

  }
}
