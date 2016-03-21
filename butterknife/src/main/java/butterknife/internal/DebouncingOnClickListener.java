package butterknife.internal;

import android.view.View;

/**
 * A {@linkplain View.OnClickListener click listener} that debounces multiple clicks posted in the
 * same frame. A click on one button disables all buttons for that frame.
 *
 * 消除抖动 的 导致一下 点击多次的  OnclickListener
 */
public abstract class DebouncingOnClickListener implements View.OnClickListener {
	static boolean enabled = true;

	private static final Runnable ENABLE_AGAIN = new Runnable() {
		@Override
		public void run() {
			enabled = true;
		}
	};

	@Override
	public final void onClick(View v) {
		if (enabled) {
			enabled = false;
			// 通过paost 来处理延时判断
			v.post(ENABLE_AGAIN);
			doClick(v);
		}
	}

	public abstract void doClick(View v);
}
