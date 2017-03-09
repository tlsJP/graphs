import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
        Paint redColor = Color.rgb(255, 0, 0);
        Rectangle red = new Rectangle(size, size, redColor);

        Rectangle red2 = new Rectangle(size, size, redColor);


        System.out.println(red.hashCode());
        System.out.println(red2.hashCode());
        assertEquals(red, red2);
    }
}
