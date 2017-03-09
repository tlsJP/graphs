import com.jp.graphs.client.EquatableRectangle;
import com.jp.graphs.core.GridVertex;
import com.jp.graphs.stereotypes.Vertex;
import javafx.scene.shape.Rectangle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by JP on 3/8/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class Sandbox {

    @Test
    public void foo() {
        int size = 3;
        Rectangle red = new EquatableRectangle(size, size, 1,1);

        Rectangle red2 = new EquatableRectangle(size, size, 1,1);

        System.out.println(red.hashCode());
        System.out.println(red2.hashCode());
        assertEquals(red, red2);

        Vertex v = new GridVertex(0, 0);
        v.setDataElement(red2);


        assertEquals(red, v.getDataElement());

    }
}
