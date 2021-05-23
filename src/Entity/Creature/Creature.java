package Entity.Creature;
import java.awt.Rectangle;

import API.Animation;
import Entity.Entity;
import Game.Game;
import Game.GameStart;
public abstract class Creature extends Entity{
    protected int MAXHP;
	protected int HP;
    protected float Speed;
    protected float deltaX,deltaY;
    protected Rectangle bounds;
    protected boolean allowAttack,dead;
    protected Attack attack;
    protected Animation creature_up,creature_down,creature_left,creature_right;


    public Creature(Game game, float x, float y, int width, int height) {
    	super(game,x, y, width, height);
        this.deltaX = 0;
        this.deltaY = 0;
        bounds = new Rectangle(0, 0, width, height);  
	}

	public void init(){

    }

    public boolean allowedAttack(){
        return this.allowAttack;
    }

    public void state_update(){

    }

    public void attackOther(Creature Other){

    }

    public Rectangle getCollisionBounds(int range) {
		return new Rectangle((int) (x + bounds.x - range), (int) (y + bounds.y - range), bounds.width + range * 2,
				bounds.height + range * 2);
	}

    public void moveX() {
		if(x + width+deltaX >= GameStart.MAX_WIDTH || x + deltaX <= 0)
			return;
		if(y + deltaY <= 0 || y + height + deltaY>= GameStart.MAX_HEIGHT)
			return;


		if (deltaX > 0) {
			int tx = (int) (x + deltaX + bounds.x + bounds.width) / 32;
			int ty = (int) (y + bounds.y) / 32;
			int tyY = (int) (y + bounds.y + bounds.height) / 32;
			if (!collisionWithTile(tx, ty) && !collisionWithTile(tx, tyY)) {
				x += deltaX;
			} else {
				x = tx * 32 + -bounds.width - 1 - bounds.x;
			}
		} else if (deltaX < 0) {
			int tx = (int) (x + deltaX + bounds.x) / 32;
			int ty = (int) (y + bounds.y) / 32;
			int tyY = (int) (y + bounds.y + bounds.height) / 32;
			if (!collisionWithTile(tx, ty) && !collisionWithTile(tx, tyY)) {
				x += deltaX;
			} else {
				x = tx * 32 + 32 - bounds.x;
			}
		}

	}
//  
	public void moveY() {
		if(x + width+deltaX >= GameStart.MAX_WIDTH || x + deltaX <= 0)
			return;
		if(y + deltaY <= 0 || y + height + deltaY>= GameStart.MAX_HEIGHT)
			return;

		if (deltaY < 0) {
			int ty = (int) ((y + deltaY + bounds.y) / 32);
			if (!collisionWithTile((int) (x + bounds.x) / 32, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / 32, ty)) {
				y += deltaY;
			} else {
				y = ty * 32 + 32 - bounds.y;
			}
		} else if (deltaY > 0) {
			int ty = (int) (y + deltaY + bounds.y + bounds.height) / 32;

			if (!collisionWithTile((int) (x + bounds.x) / 32, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / 32, ty)) {
				y += deltaY;
			} else {
				y = ty * 32 - bounds.y - bounds.height - 1;
			}
		}
	}


	public void move() {
		moveX();
		moveY();
	}
    public void die() {

	}
	public int getMAXHP() {
		return MAXHP;
	}

	public void setMAXHP(int mAXHP) {
		MAXHP = mAXHP;
	}

	protected boolean collisionWithTile(int x, int y) {
//		if (game.getTemp().getIsRock(x, y) == 1)
//			return true;
//		else
			return false;
	}
}