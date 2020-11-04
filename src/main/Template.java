package main;

import gui.Drawing;

import java.awt.*;
import java.util.List;

public class Template {

    private List<Drawing> drawings;

    public List<Drawing> getDrawings() {
        return drawings;
    }

    public void addDrawing(Drawing drawing){
        this.drawings.add(drawing);
    }

    public void addDrawings(List<Point> points, List<Color> colors){
        for(Point point : points){
             for(Color color : colors){
                 drawings.add(new Drawing(point, color));
             }
        }
    }
}
