package dk.tokebroedsted.administration.client.view;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public final class DraggableWidgetWrapper extends SimplePanel implements HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseUpHandlers {

    private boolean dragging = false;
    private int dragStartX;
    private int dragStartY;

    public DraggableWidgetWrapper(Widget widget) {
        add(widget);
        setStyleName("adjustable-item");

        addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                dragging = true;

                // capturing the mouse to the dragged widget.
                DOM.setCapture(getElement());
                dragStartX = event.getX();
                dragStartY = event.getY();
            }
        });

        addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                dragging = false;
                DOM.releaseCapture(getElement());
            }
        });

        addMouseMoveHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent event) {
                if (dragging) {
                    // we don't want the widget to go off-screen, so the top/left
                    // values should always remain be positive.
                    int newX = Math.max(0, event.getX() + getAbsoluteLeft() - dragStartX);
                    int newY = Math.max(0, event.getY() + getAbsoluteTop() - dragStartY);
                    DOM.setStyleAttribute(getElement(), "left", String.valueOf(newX));
                    DOM.setStyleAttribute(getElement(), "top", String.valueOf(newY));
                }
            }
        });
    }

    @Override
    public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
        return addDomHandler(handler, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
        return addDomHandler(handler, MouseMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
        return addDomHandler(handler, MouseUpEvent.getType());
    }
}