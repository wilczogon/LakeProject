import org.jbox2d.common.Vec2;

import com.edu.agh.student.lakeproject.fish.veiltail.Veiltail;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;



public class LakeMain {

	public static void main(String[] args){
		LakeWorld lakeWorld = new LakeWorld();
		lakeWorld.addLakeObject(new Veiltail(20.0f, new Vec2(30, 50)));
		lakeWorld.addLakeObject(new Veiltail(20.0f, new Vec2(50, 50)));
		lakeWorld.start();
	}

}
