package oyakov.controllers;

import oyakov.runtime.ConfParmSubsystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_S;

/**
 * Created by oyakovlev on 28.03.2015.
 */
public class KeyboardController implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        ConfParmSubsystem.AppContext appContext = ConfParmSubsystem.getInstance().getCtxt();

        switch(e.getKeyCode()) {
            case VK_W:
                appContext.cameraAngleX+=1.0f;
                break;
            case VK_A:
                appContext.cameraAngleY-=1.0f;
                break;
            case VK_S:
                appContext.cameraAngleX-=1.0f;
                break;
            case VK_D:
                appContext.cameraAngleY+=1.0f;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
