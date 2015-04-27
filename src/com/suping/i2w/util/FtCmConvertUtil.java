package com.suping.i2w.util;

/**
 * 厘米，英尺英寸转换工具类
 * 
 * 1英尺=12英寸   1英寸=8英分
 * 1英寸=0.0254米=2.54厘米
 * 1英尺=30.48厘米
 * 
 * @author Administrator
 * 
 */
public class FtCmConvertUtil {

	/**
	 * 传入最大最小厘米值，转换获取英尺的最大值
	 * 
	 * @param minHeight
	 * @param maxHeight
	 * @return
	 */
	public static int getMaxHeightFoot(int minHeight, int maxHeight) {
		int max = (int) (minHeight / 30.48);
		for (int i = minHeight + 1; i <= maxHeight; i++) {
			int foot = (int) (i / 30.48);
			if (foot > max) {
				max = foot;
			}
		}
		return max;
	}

	/**
	 * 传入最大最小厘米值，转换获取英尺的最小值
	 * 
	 * @param minHeight
	 * @param maxHeight
	 * @return
	 */
	public static int getMinHeightFoot(int minHeight, int maxHeight) {
		int min = (int) (minHeight / 30.48);
		for (int i = minHeight + 1; i <= maxHeight; i++) {
			int foot = (int) (i / 30.48);
			if (foot < min) {
				min = foot;
			}
		}
		return min;
	}

	/**
	 * 传入最大最小厘米值，转换获取除去英尺之后剩余英寸的最大值
	 * 
	 * @param minHeight
	 * @param maxHeight
	 * @return
	 */
	public static int getMaxHeightInch(int minHeight, int maxHeight) {
		int max = (int) (12 * (minHeight / 30.48 - (int) (minHeight / 30.48)));
		for (int i = minHeight + 1; i <= maxHeight; i++) {
			int foot = (int) (i / 30.48);
			int inch = (int) ((i - foot * 30.48) / 2.54);
			if (inch > max) {
				max = inch;
			}
		}
		return max;
	}

	/**
	 * 传入最大最小厘米值，转换获取除去英尺之后剩余英寸的最小值
	 * 
	 * @param minHeight
	 * @param maxHeight
	 * @return
	 */
	public static int getMinHeightInch(int minHeight, int maxHeight) {
		int min = (int) (12 * (minHeight / 30.48 - (int) (minHeight / 30.48)));
		for (int i = minHeight + 1; i <= maxHeight; i++) {
			int foot = (int) (i / 30.48);
			int inch = (int) ((i - foot * 30.48) / 2.54);
			if (inch < min) {
				min = inch;
			}
		}
		return min;
	}

	/**
	 * 传入厘米值，获取英尺值
	 * 
	 * @param cm
	 * @return
	 */
	public static int cm2Foot(int cm) {
		int foot = (int) (cm / 30.48);
		return foot;
	}

	/**
	 * 传入厘米值，获取减去英尺之外剩余的英寸值
	 * 
	 * @param cm
	 * @return
	 */
	public static int cm2InchMinusFoot(int cm) {
		int foot = (int) (cm / 30.48);
		int inch = (int) ((cm - foot * 30.48) / 2.54);
		return inch;
	}

	/**
	 * 传入英尺英寸值，计算相应的厘米值
	 * 
	 * @param foot
	 * @param inch
	 * @return
	 */
	public static int footInch2Cm(int foot, int inch) {
		int cm = (int) (foot * 30.48 + inch * 2.54);
		return cm;
	}

}
