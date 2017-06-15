/**
 * 2015-3-6
 */
package com.liuzhao.divineapp.base;

import android.util.SparseArray;
import android.view.View;

/**
 * @Description:万能viewHolder
 * @author:LiXiaoSong
 * @copyright @HiSite
 * @Date:2015-3-6
 */
public class BaseViewHolder {

	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		return (T) childView;
	}
}
