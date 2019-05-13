import jason.asSyntax.*;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.logging.Logger;

public class MarsEnv extends Environment {

    public static final int CrossSize = 12;

    private CrossModel model;
    private CrossView view;

    public boolean lamp1;
    public boolean lamp2;
    public boolean lamp3;
    public boolean lamp4;

    public boolean lampoff1;
    public boolean lampoff2;
    public boolean lampoff3;
    public boolean lampoff4;

    static Logger logger = Logger.getLogger(MarsEnv.class.getName());

    public static final Term switch1 = Literal.parseLiteral("switch1");
    public static final Term switch2 = Literal.parseLiteral("switch2");
    public static final Term switch3 = Literal.parseLiteral("switch3");
    public static final Term switch4 = Literal.parseLiteral("switch4");

    public static final Term turnoff1 = Literal.parseLiteral("turnoff1");
    public static final Term turnoff2 = Literal.parseLiteral("turnoff2");
    public static final Term turnoff3 = Literal.parseLiteral("turnoff3");
    public static final Term turnoff4 = Literal.parseLiteral("turnoff4");

    public static final Term moveped = Literal.parseLiteral("move(pedestrian)");
    public static final Term movecar1 = Literal.parseLiteral("move(car1)");
    public static final Term movecar2 = Literal.parseLiteral("move(car2)");
    public static final Term movecar3 = Literal.parseLiteral("move(car3)");
    public static final Term movecar4 = Literal.parseLiteral("move(car4)");
    public static final Term movenino = Literal.parseLiteral("move(nino)");

    @Override
    public void init(String[] args) {
        model = new CrossModel();
        view = new CrossView(model);
        model.setView(view);

        lamp1 = true;
        lamp2 = false;
        lamp3 = true;
        lamp4 = false;

        lampoff1 = true;
        lampoff2 = true;
        lampoff3 = true;
        lampoff4 = true;

    }

    @Override
    public boolean executeAction(String agName, Structure action) {

        logger.info(agName + " doing: " + action);

        try {

            if (action.equals(switch1)) {
                lamp1 = !lamp1;
                lampoff1 = false;
                model.switchLamp1();
            } else if (action.equals(switch2)) {
                lamp2 = !lamp2;
                lampoff2 = false;
                model.switchLamp2();

            } else if (action.equals(switch3)) {
                lamp3 = !lamp3;
                lampoff3 = false;
                model.switchLamp3();

            } else if (action.equals(switch4)) {
                lamp4 = !lamp4;
                lampoff4 = false;
                model.switchLamp4();

            } else if (action.equals(moveped)) {
                model.movePedastrian();
            } else if (action.equals(movecar1)) {
                model.moveCar1();
            } else if (action.equals(movecar2)) {
                model.moveCar2();
            } else if (action.equals(movecar3)) {
                model.moveCar3();
            } else if (action.equals(movecar4)) {
                model.moveCar4();
            } else if (action.equals(turnoff1)) {
                lampoff1 = true;
            } else if (action.equals(turnoff2)) {
                lampoff2 = true;
            } else if (action.equals(turnoff3)) {
                lampoff3 = true;
            } else if (action.equals(turnoff4)) {
                lampoff4 = true;
            } else if (action.equals(movenino)) {
                model.moveNino();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(700);
        } catch (Exception e) {
        }
        informAgsEnvironmentChanged();
        return true;

    }

    void ninoArrived() {

        Literal arr = Literal.parseLiteral("ninoarrived");

        addPercept("nino", arr);
        informAgsEnvironmentChanged();
    }

    void ninoComing() {

        Literal com = Literal.parseLiteral("ninocoming");

        addPercept("nino", com);
        informAgsEnvironmentChanged();
    }

    @Override
    public void stop() {
        super.stop();
    }

    class CrossModel extends GridWorldModel {

        private CrossModel() {
            super(CrossSize, CrossSize, 10);

            try {

                // pedastrian
                setAgPos(0, 4, 8);
                // car1
                setAgPos(1, 0, 6);
                // car2
                setAgPos(2, 5, 0);
                // car3
                setAgPos(3, 6, 11);
                // car4
                setAgPos(4, 11, 5);
                // lamp1
                setAgPos(5, 4, 7);
                // lamp2
                setAgPos(6, 4, 4);
                // lamp3
                setAgPos(7, 7, 4);
                // lamp4
                setAgPos(8, 7, 7);
                // nino
                setAgPos(9, 10, 4);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        void movePedastrian() {
            Location loc;
            loc = getAgPos(0);

            if (loc.y == 8 && loc.x >= 3 && loc.x < 8 && !getAgPos(1).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;
            } else if (loc.x == 8 && loc.y <= 8 && loc.y > 3 && !getAgPos(1).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y - 1))) {
                loc.y--;
            } else if (loc.y == 3 && loc.x > 3 && loc.x <= 8 && !getAgPos(1).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x - 1, loc.y))) {
                loc.x--;
            } else if (loc.x == 3 && loc.y < 8 && loc.y >= 3 && !getAgPos(1).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y + 1))) {
                loc.y++;
            }

            setAgPos(0, loc);
        }

        void moveCar1() {
            Location loc;
            loc = getAgPos(1);
            if (loc.y == 6 && loc.x >= 0 && loc.x <= 10 && loc.x != 4
                    && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;
            } else if (loc.y == 5 && loc.x >= 1 && loc.x <= 11 && loc.x != 7
                    && !getAgPos(0).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x - 1, loc.y))) {
                loc.x--;
            } else if (loc.x == 11 && loc.y == 6 && !getAgPos(0).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y - 1))) {
                loc.y = 5;
            } else if (loc.x == 0 && loc.y == 5 && !getAgPos(0).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y + 1))) {
                loc.y = 6;
            } else if (loc.x == 4 && loc.y == 6 && lampoff1 && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y)) && !getAgPos(0).equals(new Location(6, 7))
                    && !getAgPos(2).equals(new Location(6, 7)) && !getAgPos(3).equals(new Location(6, 7))
                    && !getAgPos(4).equals(new Location(6, 7))) {
                loc.x++;

            } else if (loc.x == 7 && loc.y == 5 && lampoff3 && !getAgPos(0).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x - 1, loc.y)) && !getAgPos(0).equals(new Location(5, 4))
                    && !getAgPos(2).equals(new Location(5, 4)) && !getAgPos(3).equals(new Location(5, 4))
                    && !getAgPos(4).equals(new Location(5, 4))) {
                loc.x--;

            } else if (loc.y == 6 && loc.x == 4 && lamp1 && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;
            } else if (loc.y == 5 && loc.x == 7 && lamp3 && !getAgPos(2).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x - 1, loc.y))) {
                loc.x--;
            }
            setAgPos(1, loc);
        }

        void moveCar2() {
            Location loc;
            loc = getAgPos(2);
            if (loc.x == 5 && loc.y >= 0 && loc.y <= 10 && loc.y != 4
                    && !getAgPos(3).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(1).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y + 1))) {
                loc.y++;
            } else if (loc.x == 6 && loc.y >= 1 && loc.y <= 11 && loc.y != 7
                    && !getAgPos(3).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(1).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y - 1))) {
                loc.y--;
            } else if (loc.x == 5 && loc.y == 11 && !getAgPos(1).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y))) {
                loc.x = 6;
            } else if (loc.x == 6 && loc.y == 0 && !getAgPos(1).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x - 1, loc.y))) {
                loc.x = 5;
            } else if (loc.x == 5 && loc.y == 4 && lampoff2) {
                if (!getAgPos(1).equals(new Location(loc.x, loc.y + 1))
                        && !getAgPos(3).equals(new Location(loc.x, loc.y + 1))
                        && !getAgPos(0).equals(new Location(loc.x, loc.y + 1))
                        && !getAgPos(4).equals(new Location(loc.x, loc.y + 1))
                        && !getAgPos(1).equals(new Location(4, 6)) && !getAgPos(3).equals(new Location(4, 6))
                        && !getAgPos(4).equals(new Location(4, 6))) {
                    loc.y++;
                }
            } else if (loc.x == 6 && loc.y == 7 && lampoff4 && !getAgPos(1).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y - 1)) && !getAgPos(1).equals(new Location(7, 5))
                    && !getAgPos(3).equals(new Location(7, 5)) && !getAgPos(4).equals(new Location(7, 5))) {
                loc.y--;

            } else if (loc.y == 4 && loc.x == 5 && lamp2 && !getAgPos(1).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y + 1))) {
                loc.y++;
            } else if (loc.y == 7 && loc.x == 6 && lamp4 && !getAgPos(1).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y - 1))) {
                loc.y--;
            }

            setAgPos(2, loc);
        }

        void moveCar3() {
            Location loc;
            loc = getAgPos(3);

            if (loc.x == 6 && loc.y >= 6 && loc.y <= 11 && loc.y != 7
                    && !getAgPos(1).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y - 1))) {
                loc.y--;
            } else if (loc.x <= 6 && loc.x >= 1 && loc.y == 5 && !getAgPos(1).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x - 1, loc.y))) {
                loc.x--;
            } else if (loc.x >= 0 && loc.x <= 3 && loc.y == 6 && !getAgPos(1).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;
            } else if (loc.x == 5 && loc.y >= 6 && loc.y <= 10 && !getAgPos(1).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y + 1))) {
                loc.y++;
            } else if (loc.x == 0 && loc.y == 5 && !getAgPos(1).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y + 1))) {
                loc.y++;
            } else if (loc.x == 5 && loc.y == 11 && !getAgPos(1).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;
            } else if (loc.x == 6 && loc.y == 7 && lampoff4 && !getAgPos(1).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y - 1)) && !getAgPos(1).equals(new Location(7, 5))
                    && !getAgPos(2).equals(new Location(7, 5)) && !getAgPos(4).equals(new Location(7, 5))
                    && !getAgPos(1).equals(new Location(5, 4)) && !getAgPos(4).equals(new Location(5, 4))
                    && !getAgPos(2).equals(new Location(5, 4))) {
                loc.y--;

            } else if (loc.x == 4 && loc.y == 6 && lampoff1 && !getAgPos(1).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;

            } else if (loc.x == 6 && loc.y == 7 && lamp4 && !getAgPos(1).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(0).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(4).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y - 1))) {
                loc.y--;
            } else if (loc.x == 4 && loc.y == 6 && lamp1 && !getAgPos(1).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(4).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;
            }

            setAgPos(3, loc);
        }

        void moveCar4() {
            Location loc;
            loc = getAgPos(4);

            if (loc.y == 6 && loc.x >= 0 && loc.x <= 10 && loc.x != 4
                    && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(1).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;
            } else if (loc.y == 5 && loc.x >= 1 && loc.x <= 11 && loc.x != 7
                    && !getAgPos(0).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(1).equals(new Location(loc.x - 1, loc.y))) {
                loc.x--;
            } else if (loc.x == 11 && loc.y == 6 && !getAgPos(0).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y - 1))
                    && !getAgPos(1).equals(new Location(loc.x, loc.y - 1))) {
                loc.y = 5;
            } else if (loc.x == 0 && loc.y == 5 && !getAgPos(0).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(2).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(3).equals(new Location(loc.x, loc.y + 1))
                    && !getAgPos(1).equals(new Location(loc.x, loc.y + 1))) {
                loc.y = 6;
            } else if (loc.x == 4 && loc.y == 6 && lampoff1 && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(1).equals(new Location(loc.x + 1, loc.y)) && !getAgPos(0).equals(new Location(6, 7))
                    && !getAgPos(2).equals(new Location(6, 7)) && !getAgPos(3).equals(new Location(6, 7))
                    && !getAgPos(1).equals(new Location(6, 7))) {
                loc.x++;

            } else if (loc.x == 7 && loc.y == 5 && lampoff3 && !getAgPos(0).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(2).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(1).equals(new Location(loc.x - 1, loc.y)) && !getAgPos(0).equals(new Location(5, 4))
                    && !getAgPos(2).equals(new Location(5, 4)) && !getAgPos(3).equals(new Location(5, 4))
                    && !getAgPos(1).equals(new Location(5, 4))) {
                loc.x--;

            } else if (loc.y == 6 && loc.x == 4 && lamp1 && !getAgPos(2).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x + 1, loc.y))
                    && !getAgPos(1).equals(new Location(loc.x + 1, loc.y))) {
                loc.x++;
            } else if (loc.y == 5 && loc.x == 7 && lamp3 && !getAgPos(2).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(0).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(3).equals(new Location(loc.x - 1, loc.y))
                    && !getAgPos(1).equals(new Location(loc.x - 1, loc.y))) {
                loc.x--;
            }
            setAgPos(4, loc);
        }

        void switchLamp1() {
            Location loc;
            loc = getAgPos(5);
            setAgPos(5, loc);
        }

        void switchLamp2() {
            Location loc;
            loc = getAgPos(6);
            setAgPos(6, loc);
        }

        void switchLamp3() {
            Location loc;
            loc = getAgPos(7);
            setAgPos(7, loc);
        }

        void switchLamp4() {
            Location loc;
            loc = getAgPos(8);
            setAgPos(8, loc);
        }

        void moveNino() {
            Location loc;
            loc = getAgPos(9);

            if (loc.x == 10 && loc.y == 4) {
                loc.y++;
                ninoComing();
            } else if (loc.x <= 10 && loc.x > 6 && loc.y == 5) {
                loc.x--;
            } else if (loc.x == 6 && loc.y <= 5 && loc.y > 1) {
                loc.y--;
            } else if (loc.x == 6 && loc.y == 1) {
                loc.x++;
                ninoArrived();
            } else if (loc.x == 7 && loc.y == 1) {
                loc.y--;
                ninoComing();
            } else if (loc.y == 0 && (loc.x == 7 || loc.x == 6)) {
                loc.x--;
            } else if (loc.x == 5 && loc.y >= 0 && loc.y <= 5) {
                loc.y++;
            } else if (loc.x >= 5 && loc.x <= 10 && loc.y == 6) {
                loc.x++;
            } else if (loc.x == 11 && (loc.y == 6 || loc.y == 5)) {
                loc.y--;
            } else if (loc.x == 11 && loc.y == 4) {
                loc.x--;
                ninoArrived();
            }
            setAgPos(9, loc);
        }

    }

    class CrossView extends GridWorldView {

        public CrossView(CrossModel model) {
            super(model, "Smart Cross", 600);
            setResizable(false);
            setVisible(true);
            repaint();
        }

        /** draw application objects */
        @Override
        public void draw(Graphics g, int x, int y, int object) {

        }

        @Override
        public void drawAgent(Graphics g, int x, int y, Color c, int id) {
            String label;
            switch (id) {
            case 0:
                label = "P";
                c = Color.orange;
                break;
            case 1:
                label = "Car1";
                c = Color.gray;
                break;
            case 2:
                label = "Car2";
                c = Color.lightGray;
                break;
            case 3:
                label = "Car3";
                c = Color.darkGray;
                break;
            case 4:
                label = "Car4";
                c = Color.black;
                break;
            case 5:
                label = " Lamp";
                if (lampoff1) {
                    c = Color.yellow;
                } else if (lamp1) {
                    c = Color.green;
                } else {
                    c = Color.red;
                }

                break;
            case 6:
                label = " Lamp";
                if (lampoff2) {
                    c = Color.yellow;
                } else if (lamp2) {
                    c = Color.green;
                } else {
                    c = Color.red;
                }
                break;
            case 7:
                label = " Lamp";
                if (lampoff3) {
                    c = Color.yellow;
                } else if (lamp3) {
                    c = Color.green;
                } else {
                    c = Color.red;
                }
                break;
            case 8:
                label = " Lamp";
                if (lampoff4) {
                    c = Color.yellow;
                } else if (lamp4) {
                    c = Color.green;
                } else {
                    c = Color.red;
                }
                break;
            case 9:
                label = "Nino";
                c = Color.blue;
                break;

            default:
                label = "-";
                break;
            }

            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.white);
            super.drawString(g, x, y, defaultFont, label);

        }

    }
}