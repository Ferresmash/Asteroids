package entities;

import view.RenderVisitor;

public interface Drawable {
    void accept(RenderVisitor visitor);
}
