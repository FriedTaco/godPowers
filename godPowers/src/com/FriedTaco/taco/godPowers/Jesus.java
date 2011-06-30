package com.FriedTaco.taco.godPowers;

import java.util.Hashtable;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Jesus
{
	int[] raftX = new int[25];
	int[] raftY = new int[25];
	int[] raftZ = new int[25];
	@SuppressWarnings("rawtypes")
	public static Hashtable rafts = new Hashtable();
	Player player;
	public Jesus()
	{
	}
	public class RaftPiece
	{
		public RaftPiece(int x, int y, int z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			
		}
		int x,y,z,type = 0;
		boolean made = false;
		boolean destroyed = false;
	}
	public class Raft
	{
		public RaftPiece[] raft =
		{
				new RaftPiece(2, -1, 2),
				new RaftPiece(2, -1, 1),
				new RaftPiece(2, -1, 0),
				new RaftPiece(2, -1, -1),
				new RaftPiece(2, -1, -2),
				new RaftPiece(1, -1, 2),
				new RaftPiece(1, -1, 1),
				new RaftPiece(1, -1, 0),
				new RaftPiece(1, -1, -1),
				new RaftPiece(1, -1, -2),
				new RaftPiece(0, -1, 2),
				new RaftPiece(0, -1, 1),
				new RaftPiece(0, -1, 0),
				new RaftPiece(0, -1, -1),
				new RaftPiece(0, -1, -2),
				new RaftPiece(-1, -1, 2),
				new RaftPiece(-1, -1, 1),
				new RaftPiece(-1, -1, 0),
				new RaftPiece(-1, -1, -1),
				new RaftPiece(-1, -1, -2),
				new RaftPiece(-2, -1, 2),
				new RaftPiece(-2, -1, 1),
				new RaftPiece(-2, -1, 0),
				new RaftPiece(-2, -1, -1),
				new RaftPiece(-2, -1, -2)
		};
	
	void makeJesusRaft(Player player)
	{
		for(int i = 0; i < raft.length; i++)
		{
			Block block = player.getWorld().getBlockAt(((int) player.getLocation().getX() + raft[i].x), ((int) player.getLocation().getY() + raft[i].y), ((int) player.getLocation().getZ() + raft[i].z));
			if(block.getTypeId() == 8 || block.getTypeId() == 9) 
			{
				raftX[i] = (int) player.getLocation().getX()+raft[i].x;
				raftY[i] = (int) player.getLocation().getY()+raft[i].y;
				raftZ[i] = (int) player.getLocation().getZ()+raft[i].z;
				raft[i].made = true;
				block.setTypeId(79);
			} 
			else if(block.getTypeId() == 10 || block.getTypeId() == 11)
			{
				raftX[i] = (int) player.getLocation().getX()+raft[i].x;
				raftY[i] = (int) player.getLocation().getY()+raft[i].y;
				raftZ[i] = (int) player.getLocation().getZ()+raft[i].z;
				raft[i].made = true;
				block.setTypeId(49);
			}
			else 
			{
				raft[i].made = false;
			}
		}
	}
	void destroyJesusRaft(Player player)
	{
		for(int i = 0; i < raft.length; i++)
		{
			Block block = player.getWorld().getBlockAt(((int)raftX[i]), ((int)raftY[i]), ((int)raftZ[i]));
			if(block.getTypeId() == 79)
			{
				block.setTypeId(8);
			}
			else if(block.getTypeId() == 49)
			{
				block.setTypeId(10);
			}
			if (raft[i].made)
			{
				raft[i].made = false;
			}
		}
	}
	}
}
