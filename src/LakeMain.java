import org.jbox2d.common.Vec2;

import com.edu.agh.student.lakeproject.fish.mousecontrolled.MouseControlledFish;
import com.edu.agh.student.lakeproject.fish.veiltail.Veiltail;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;



public class LakeMain {

	public static void main(String[] args){
		LakeWorld lakeWorld = new LakeWorld();
		lakeWorld.addLakeObject(new Veiltail(lakeWorld, 20.0f, new Vec2(300, 300)));
		lakeWorld.addLakeObject(new Veiltail(lakeWorld, 20.0f, new Vec2(250, 350)));
		lakeWorld.addLakeObject(new Obstacle(lakeWorld, 50.0f, new Vec2(100, 200)));
		lakeWorld.addLakeObject(new MouseControlledFish(lakeWorld, 50.0f, new Vec2(400, 400)));
		lakeWorld.start();
	}

}
