package annotations;

import java.lang.annotation.*;


//1. @interface -> custom annotation 
//2. @Target -> mention - executed in class / method 
//3. @Retention -> execution of your annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
 @interface ValidateAmount {
	 double min() default 0.0;

}
