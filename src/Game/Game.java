package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import Game.Music.Music;
import Game.Setting.KeyAction;
import Game.Setting.Setting;
import World.Map;



public class Game implements Runnable { 
	private Display display;
	public int width, height,currentMap;
	public String title;
	private Thread thread;
	private boolean running = false;
	private String musicFilePath;
	private Music music;
	private KeyAction key;
	private BufferStrategy bs;
	private Graphics g;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		key = new KeyAction();

	}
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
		musicFilePath = "src/Assets/nhac.wav";
		music = new Music(musicFilePath);
		if(Setting.musicable==true)
			music.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		key.update();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		bs.show();
		g.dispose();
	}
	public void run() {
		init();
		int fps = 30;
		double timePerTick = 1000000000.0/fps;
		double delta = 0;
		long now; 
		long lastTime = System.nanoTime();
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			lastTime = now;
			if(delta >=1 )
			{
				update();
				render();
				delta -- ;
			}

		}
		stop();
	}

	private void init() {
		display = new Display(title, width, height);
	}
	public KeyAction getKeyaction() {
		return key;
	}
}
