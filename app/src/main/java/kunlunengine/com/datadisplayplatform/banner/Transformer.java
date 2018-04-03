package kunlunengine.com.datadisplayplatform.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import kunlunengine.com.datadisplayplatform.banner.transformer.AccordionTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.BackgroundToForegroundTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.CubeInTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.CubeOutTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.DefaultTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.DepthPageTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.FlipHorizontalTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.FlipVerticalTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.ForegroundToBackgroundTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.RotateDownTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.RotateUpTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.ScaleInOutTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.StackTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.TabletTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.ZoomInTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.ZoomOutSlideTransformer;
import kunlunengine.com.datadisplayplatform.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
