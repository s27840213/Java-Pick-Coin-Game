package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	private Clip clip;
	public AudioPlayer(String s){
		try {
			AudioInputStream ais = 
					AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			AudioFormat baseformat = ais.getFormat();	
			AudioFormat decodeFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_UNSIGNED,
					baseformat.getSampleRate(),
					16,
					baseformat.getChannels(),
					baseformat.getChannels()*2,
					baseformat.getSampleRate(),
					false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(
					decodeFormat,ais);
			clip = AudioSystem.getClip();
			clip.open(dais);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void playLoop() {
		if(clip==null) {
			return;
		}
		stop();
		clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		
	}
	public void playOnce() {
		if(clip==null) {
			return;
		}
		stop();
		clip.setFramePosition(0);
		clip.start();
		
	}
	public void stop() {
		if(clip.isRunning())
			clip.stop();
	}
	public void close() {
	clip.close();
	}
}
