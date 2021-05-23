package World;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import API.Texture;
import Entity.Tile.BigTree;
import Entity.Tile.Chest;
import Entity.Tile.ConcreteColumn;
import Entity.Tile.FlagBlack;
import Entity.Tile.FlagBlue;
import Entity.Tile.FlagRed;
import Entity.Tile.Flower;
import Entity.Tile.Grass;
import Entity.Tile.Grave;
import Entity.Tile.Hedge;
import Entity.Tile.House;
import Entity.Tile.HouseNormal;
import Entity.Tile.Lake;
import Entity.Tile.Land;
import Entity.Tile.Pumpkin;
import Entity.Tile.Puppet;
import Entity.Tile.RoadSign;
import Entity.Tile.Tree;
import Entity.Tile.Water;

import java.awt.Rectangle;


public class Map {
	// copy cua Tuyen (Minh)
	private int map[][];
	private Lake lake;
	private Rectangle[] rect;
	private Land land;
	private Tree tree;
	private Grass grass;
	private Water water;
	private Flower flower;
	private BigTree bigTree;
	private Grave grave;
	private Chest chest;
	private FlagBlue flagBlue;
	private FlagBlack flagBlack;
	private Pumpkin pumpkin;
	private Puppet puppet;
	private Hedge hedge;
	private RoadSign roadSign;
	private FlagRed flagRed;
	private House house;
	private HouseNormal houseNormal;
	private ConcreteColumn column;

	private int[] isRock;

	// ket thuc sua
	public Map(String path) {
		getMapFromFile(path);
		init();
	}

	// khoi tao cac doi tuong trong map
	public void init() {
		// Copy tu Tuyen(Minh)
		rect = new Rectangle[2];
		lake = new Lake();
		land = new Land();
		grass = new Grass();
		tree = new Tree();
		water = new Water();
		chest = new Chest();
		flower = new Flower();
		bigTree = new BigTree();
		column = new ConcreteColumn();
		grave = new Grave();
		flagBlue = new FlagBlue();
		flagBlack = new FlagBlack();
		flagRed = new FlagRed();
		house = new House();
		houseNormal = new HouseNormal();
		pumpkin = new Pumpkin();
		puppet = new Puppet();
		hedge = new Hedge();
		roadSign = new RoadSign();
		// End
		// ket thuc sua
	}

	// doc map tu ma tran
	public void getMapFromFile(String path) {
		ArrayList<ArrayList<Integer>> map_ = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String temp;
			while ((temp = br.readLine()) != null) {
				String temp1[] = temp.trim().split(" ");
				if (temp.isEmpty()) {
					continue;
				}
				ArrayList<Integer> row = new ArrayList<>();
				for (String string : temp1) {
					int x = Integer.parseInt(string);
					row.add(x);
				}
				map_.add(row);
			}
			map = new int[map_.size()][map_.get(0).size()];

			for (int i = 0; i < map_.size(); i++) {
				for (int j = 0; j < map_.get(0).size(); j++) {
					map[i][j] = map_.get(i).get(j);
					// System.out.print(map[i][j] + " ");
				}
				// System.out.println();
			}
			// System.out.println();

			//

			isRock = new int[map_.size() * ((int) map_.get(0).size()) + 1];
			for (int i = 0; i <= map_.size() * ((int) map_.get(0).size()); i++)
				isRock[i] = 0;

			//
			Texture.loadTextures();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Copy tu a Tuyen
	public void renderRect(int x1, int y1, int x2, int y2) {
		rect[0] = new Rectangle(x1, y1, 32, 32);
		rect[1] = new Rectangle(x2, y2, 32, 32);
	}

	public Rectangle[] getRectangle() {
		return this.rect;
	}

	//

	public int getIsRock(int x, int y) {
		int a = y;
		int b = x;
		// System.out.println(a + " " + b + " " + isRock[map[a][b]]);
		return isRock[map[a][b]];
	}

	//

	// ve map
	public void render(Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] >= 1 && map[i][j] <= 15) {
					land.setId(map[i][j]);
					land.render(g, j, i);
				} else if (map[i][j] == 0) {
					grass.render(g, j, i);
				} else if (map[i][j] == 18) {
					grass.render(g, j, i);
					tree.render(g, j, i);
					isRock[map[i][j]] = 1;
				} else if (map[i][j] >= 21 && map[i][j] <= 35) {
					grass.render(g, j, i);
					water.setId(map[i][j] - 20);
					water.render(g, j, i);
					isRock[map[i][j]] = 1;
				} else if (map[i][j] == 16) {
					grass.render(g, j, i);
					flower.render(g, j, i);
					isRock[map[i][j]] = 1;
				} else if (map[i][j] >= 41 && map[i][j] <= 56) {
					grass.render(g, j, i);
					bigTree.setId(map[i][j] - 40);
					bigTree.render(g, j, i);
					isRock[map[i][j]] = 1;
				} else if (map[i][j] == 40 || map[i][j] == 39) {
					grass.render(g, j, i);
					grave.setId(map[i][j] - 39);
					grave.render(g, j, i);
					isRock[map[i][j]] = 1;
				} else if (map[i][j] >= 101 && map[i][j] <= 103) {
					grass.render(g, j, i);
					flagBlue.setId(map[i][j] - 100);
					flagBlue.render(g, j, i);
				} else if (map[i][j] >= 104 && map[i][j] <= 106) {
					grass.render(g, j, i);
					flagBlack.setId(map[i][j] - 103);
					flagBlack.render(g, j, i);
				} else if (map[i][j] >= 107 && map[i][j] <= 109) {
					grass.render(g, j, i);
					flagRed.setId(map[i][j] - 106);
					flagRed.render(g, j, i);
				} else if (map[i][j] >= 110 && map[i][j] <= 129) {
					grass.render(g, j, i);
					house.setId(map[i][j] - 109);
					house.render(g, j, i);
					isRock[map[i][j]] = 1;
				} else if (map[i][j] >= 130 && map[i][j] <= 147) {
					grass.render(g, j, i);
					houseNormal.setId(map[i][j] - 129);
					houseNormal.render(g, j, i);
					isRock[map[i][j]] = 1;
				} else if (map[i][j] == 90) {
					grass.render(g, j, i);
					pumpkin.render(g, j, i);
				} else if (map[i][j] == 92 || map[i][j] == 93) {
					grass.render(g, j, i);
					puppet.setId(map[i][j] - 91);
					puppet.render(g, j, i);
				} else if (map[i][j] >= 94 && map[i][j] <= 99) {
					grass.render(g, j, i);
					hedge.setId(map[i][j] - 93);
					hedge.render(g, j, i);
				} else if (map[i][j] >= 87 && map[i][j] <= 89) {
					grass.render(g, j, i);
					roadSign.setId(map[i][j] - 86);
					roadSign.render(g, j, i);
				}
			}
		}
	}
}