package converter;

//We used JAVE (Java Audio Video Encoder ) maven dependency to convert the media files
//Contributors ----> Pls change the file path before running the application



import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;

public class MainConverter {

  public static void main(String[] args){
      


	  mp4TOmp3();
  }

  public static void mp4TOmp3(){

      try {
          File source = new File("G:\\Dropbox\\Education\\edX\\W3Cx\\HTML5.1x\\Weeks\\W2 - HTML5 Multimedia\\W2S2.2U3_Ex1_video.mp4");
          File target = new File("G:\\Dropbox\\Education\\edX\\W3Cx\\HTML5.1x\\Weeks\\W2 - HTML5 Multimedia\\W2S2.2U3_Ex1_video.mp3");

          //Audio Attributes
          AudioAttributes audio = new AudioAttributes();
          audio.setCodec("libmp3lame");
          audio.setBitRate(128000);
          audio.setChannels(2);
          audio.setSamplingRate(44100);

          //Encoding attributes
          EncodingAttributes attrs = new EncodingAttributes();
          attrs.setFormat("mp3");
          attrs.setAudioAttributes(audio);

          //Encode
          Encoder encoder = new Encoder();
          encoder.encode(new MultimediaObject(source), target, attrs);

      } catch (Exception ex) {
          //ex.printStackTrace();
          boolean succeeded = false;
      }

  }

//  public void mp4TOmkv(){
//
//      try {
//          File source = new File("/Users/admin/Documents/mediaConvertor/media/mp4Tomkv/deisgnpattern1.mp4");
//          File target = new File("/Users/admin/Documents/mediaConvertor/mediaConverted/mp4Tomkv-converted/deisgnpattern1.mkv");
//
//          //Audio Attributes
//          AudioAttributes audio = new AudioAttributes();
//          audio.setCodec("libmp3lame");
//          audio.setBitRate(new Integer(64000));
//          audio.setChannels(new Integer(1));
//          audio.setSamplingRate(new Integer(22050));
//
//          //video Attributes
//          VideoAttributes video = new VideoAttributes();
//          video.setCodec("mpeg4");
//          video.setBitRate(new Integer(160000));
//          video.setFrameRate(new Integer(15));
//          video.setSize(new VideoSize(400, 300));
//
//          //Encoding attributes
//          EncodingAttributes attrs = new EncodingAttributes();
//          attrs.setFormat("matroska");
//          attrs.setAudioAttributes(audio);
//          attrs.setVideoAttributes(video);
//
//          //Encode
//          Encoder encoder = new Encoder();
//          encoder.encode(new MultimediaObject(source), target, attrs);
//
//      } catch (Exception ex) {
//          //ex.printStackTrace();
//          boolean succeeded = false;
//      }
//
//  }
//
//
//  public void mp4TOflv(){
//
//      try {
//          File source = new File("/Users/admin/Documents/mediaConvertor/media/mp4Toflv/deisgnpattern1.mp4");
//          File target = new File("/Users/admin/Documents/mediaConvertor/mediaConverted/mp4Toflv-converted/deisgnpattern1.flv");
//
//          //Audio Attributes
//          AudioAttributes audio = new AudioAttributes();
//          audio.setCodec("libmp3lame");
//          audio.setBitRate(new Integer(64000));
//          audio.setChannels(new Integer(1));
//          audio.setSamplingRate(new Integer(22050));
//
//          //video Attributes
//          VideoAttributes video = new VideoAttributes();
//          video.setCodec("flv");
//          video.setBitRate(new Integer(160000));
//          video.setFrameRate(new Integer(15));
//          video.setSize(new VideoSize(400, 300));
//
//          //Encoding attributes
//          EncodingAttributes attrs = new EncodingAttributes();
//          attrs.setFormat("flv");
//          attrs.setAudioAttributes(audio);
//          attrs.setVideoAttributes(video);
//
//          //Encode
//          Encoder encoder = new Encoder();
//          encoder.encode(new MultimediaObject(source), target, attrs);
//
//      } catch (Exception ex) {
//          //ex.printStackTrace();
//          boolean succeeded = false;
//      }
//
//  }
}