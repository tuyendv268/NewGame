package Entity.Creature;
import java.awt.Color;
import java.awt.Graphics;

import API.Animation;
import API.Texture;
import Game.Game;
import Game.GameStart;

public class Player extends Creature{
	private int dem = 0;
    private int previous_state = 0;
    private boolean isAttack = false;
    private long lastTimeAttack;
	private long cooldown = 1000, time = 5000, lasttime = 0;
    public Player(Game game,float x,float y,int width, int height){
        super(game, x, y, width, height);

        creature_up = new Animation(Texture.player_up, 300);
		creature_down = new Animation(Texture.player_down, 300);
		creature_left = new Animation(Texture.player_left, 300);
		creature_right = new Animation(Texture.player_right, 300);

		attack_up = new Animation(Texture.attack_up, 100);
		attack_down = new Animation(Texture.attack_down, 100);
		attack_left = new Animation(Texture.attack_left, 100);
		attack_right = new Animation(Texture.attack_right, 100);
        
        bounds.x = 10;
        bounds.y = 20;
        bounds.width = 6;
        bounds.height = 8;
    }

	private boolean checkAttack() {
		time += System.currentTimeMillis() - lasttime;

		if (isAttack && (time >= cooldown)) {
			time = 0;
			lasttime = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	private boolean checkTime() {
		time = System.currentTimeMillis() - lasttime;
		if (time > cooldown) {

			time = 0;
			System.out.println(lasttime);
			lasttime = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	private void Time_attack() {

		if (isAttack == false)
			return;

		if (dem < Texture.getLengthBufferedImage(Texture.attack_right) * 5)
			return;

		System.out.println("hetime");
		if (checkTime()) {
			isAttack = true;
			dem = 0;
		} else
			isAttack = false;
	}

	public boolean isAttack() {
		return isAttack;
	}

    @Override
    public void update() {
        move_update();
		loca_update();
		move();
    }


    private void move_update() {
		creature_up.update();
		creature_down.update();
		creature_left.update();
		creature_right.update();
	}

    private void loca_update() { // update vi tri
		deltaX = 0.f;
		deltaY = 0.f;
		if (game.getKeyaction().up)
			if (y > 0)
				deltaY = -3.5f;
		if (game.getKeyaction().down)
			if (y < GameStart.MAX_HEIGHT - 32)
				deltaY = 3.5f;
		if (game.getKeyaction().left)
			if (x > 0)
				deltaX = -3.5f;
		if (game.getKeyaction().right)
			if (x < GameStart.MAX_WIDTH - 32)
				deltaX = 3.5f;
		if (game.getKeyaction().attack) {
			isAttack = true;
		}
	}
    public void drawLeft(Graphics g) {
		if (!isAttack)
			g.drawImage(creature_left.getCurrentImage(Texture.player_left), (int) x, (int) y, width, height, null);
		else {
			g.drawImage(attack_left.getCurrentImage(Texture.attack_left), (int) x, (int) y, width, height, null);
			dem++;
		}
		previous_state = 1;
	}

	public void drawRight(Graphics g) {
		if (!isAttack)
			g.drawImage(creature_right.getCurrentImage(Texture.player_right), (int) x, (int) y, width, height, null);
		else {

			g.drawImage(attack_right.getCurrentImage(Texture.attack_right), (int) x, (int) y, width, height, null);
			dem++;
		}
		previous_state = 2;
	}

	public void drawUp(Graphics g) {
		if (!isAttack)
			g.drawImage(creature_up.getCurrentImage(Texture.player_up), (int) x, (int) y, width, height, null);
		else {
			g.drawImage(attack_up.getCurrentImage(Texture.attack_up), (int) x, (int) y, width, height, null);
			dem++;
		}
		previous_state = 3;
	}

	public void drawDown(Graphics g) {
		if (!isAttack)
			g.drawImage(creature_down.getCurrentImage(Texture.player_down), (int) x, (int) y, width, height, null);
		else {
			g.drawImage(attack_down.getCurrentImage(Texture.attack_down), (int) x, (int) y, width, height, null);
			dem++;
		}
		previous_state = 4;
	}

    @Override
	public void render(Graphics g) {
		if (deltaX == 0 && deltaY == 0) {
			if (previous_state == 0) {
				drawRight(g);
			}
			if (previous_state == 1)
				drawLeft(g);
			if (previous_state == 2)
				drawRight(g);
			if (previous_state == 3)
				drawUp(g);
			if (previous_state == 4)
				drawDown(g);
		}
		if (deltaX < 0) {
			drawLeft(g);
		}
		if (deltaX > 0) {
			drawRight(g);
		}
		if (deltaY < 0) {
			drawUp(g);
		}
		if (deltaY > 0) {
			drawDown(g);
		}
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y - 4, 30, 4);
		g.setColor(Color.red);
		 g.fillRect((int) x, (int) y - 4, this.HP / 10, 4);
	}
}
