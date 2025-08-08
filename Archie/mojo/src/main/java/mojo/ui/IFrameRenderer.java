package mojo.ui;


import mojo.ui.exception.UIException;
import mojo.km.messaging.IEvent;
/** @modelguid {777659C4-00F5-4E95-A850-87262585F04E} */
public interface IFrameRenderer {

	/** @modelguid {5E79C7B2-1ED5-4796-BB2C-4E6F7854286C} */
public void renderFrame(IFrame aFrame, IEvent aMessage) throws UIException;}