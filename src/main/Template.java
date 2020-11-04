package main;

import gui.Drawing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Template {

    private List<Drawing> drawings;

    public Template(Drawing drawing) {
        drawings = new ArrayList<>();
        drawings.add(drawing);
    }

    public Template(List<Point> points, List<Color> colors) {

        drawings = new ArrayList<>();
        for(var index = 0; index < points.size(); index++){

            var point = points.get(index);
            var color = colors.get(index);

            drawings.add(new Drawing(point, color));
        }
    }

    public List<Drawing> getDrawings() {
        return drawings;
    }
    public void addDrawing(Drawing drawing){
        this.drawings.add(drawing);
    }

    public void addDrawings(List<Point> points, List<Color> colors){

        for(var index = 0; index < points.size(); index++){

            var point = points.get(index);
            var color = colors.get(index);

            drawings.add(new Drawing(point, color));
        }
    }
}
