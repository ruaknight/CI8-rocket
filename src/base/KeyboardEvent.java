package base;

import game.player.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvent extends JFrame{
    static public KeyboardEvent keyboardEvent = new KeyboardEvent();
    public double angle;
    public int multiplier;

    private KeyboardEvent() {
        this.angle = 5.0;
        this.multiplier = 1;
    }

    public void runKeyboard() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    angle = -5.0;
                    System.out.println("turn left");
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    angle = 5.0;
                    System.out.println("turn right");
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    multiplier = 2;
                    System.out.println("speed up");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}

//    static public KeyboardEvent keyboardEvent = new KeyboardEvent();
//    public double angle = 0.0;
//    public int multiplier = 1;
//    public GameObject temp;
//
//    private KeyboardEvent() {
//
////    public void runKeyboard() {
////        this.addKeyListener(new KeyListener() {
////
////            @Override
////            public void keyTyped(KeyEvent e) {
////            }
////
////            @Override
////            public void keyPressed(KeyEvent e) {
////                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
////                    temp.position.rotate(-5.0);
////                    System.out.println("turn left");
////                }
////                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
////                    temp.position.rotate(5.0);
////                    System.out.println("turn right");
////                }
////
////                if (e.getKeyCode() == KeyEvent.VK_UP) {
////                    multiplier = 2;
////                    System.out.println("speed up");
////                }
////            }
////
////            @Override
////            public void keyReleased(KeyEvent e) {
////                if (e.getKeyCode() == KeyEvent.VK_UP) {
////                    multiplier = 1;
////                    System.out.println("reset speed");
////                }
////            }
////        }); {
////
////        }
////    }
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//        public void keyPressed(KeyEvent e) {
//            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                temp.position.rotate(-5.0);
//                System.out.println("turn left");
//            }
//            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                temp.position.rotate(5.0);
//                System.out.println("turn right");
//            }
//
//            if (e.getKeyCode() == KeyEvent.VK_UP) {
//                multiplier = 2;
//                System.out.println("speed up");
//            }
//        }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_UP) {
//            multiplier = 1;
//            System.out.println("reset speed");
//        }
//    }
//}
