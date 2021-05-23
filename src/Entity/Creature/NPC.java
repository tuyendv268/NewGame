package Entity.Creature;

import java.util.concurrent.ThreadLocalRandom;

import Game.Game;

public class NPC extends Creature{
	protected float Center_x, Center_y;
	protected double R;
	protected long timeMove;
	protected boolean dead = false;
	protected Player player;
	protected boolean isAttack;
	
	
	public NPC(Game game, Player player, float x, float y, int width, int height) {
		super(game, x, y, width, height);
		this.player = player;
		Center_x = 200;
		Center_y = 200;
		R = 200.0;
		isAttack = false;
		timeMove = 0;
		dead = false;
	}
	
	public float getCenter_x() {
		return Center_x;
	}

	public void setCenter_x(float center_x) {
		Center_x = center_x;
	}

	public float getCenter_y() {
		return Center_y;
	}

	public void setCenter_y(float center_y) {
		Center_y = center_y;
	}

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}

	public void die() {
		if (this.HP <= 0)
			dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public void moveX() {
		deltaX = 0;
		deltaY = 0;
		
		if (check_index(x, y)) {
			if (ThreadLocalRandom.current().nextInt(1, 100) < 50)
			{
				deltaX = -2.0f;
			}
			else deltaX = 2.0f;
		} else {
			if (Center_x > x) {
				deltaX = 2.0f;
			} else
				deltaX = -2.0f;
		}

	}


	public void moveY() {
		deltaX = 0;
		deltaY = 0;

		if (check_index(this.x, this.y)) {
			if (ThreadLocalRandom.current().nextInt(1, 100) < 50)
				deltaY = -2.0f;
			else
				deltaY = 2.0f;
		} else {
			if (Center_y > y) {
				deltaY = 2.0f;
			} else
				deltaY = -2.0f;
		}
	}
	
	public boolean outTherange() {
		if(Math.sqrt((x - Center_x) * (x - Center_x) + (y - Center_y) * (y - Center_y)) > R)
				return true;
		return false;
	}
	
}
