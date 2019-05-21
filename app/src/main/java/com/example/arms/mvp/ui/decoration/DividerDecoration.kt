package com.example.arms.mvp.ui.decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter


class DividerDecoration : androidx.recyclerview.widget.RecyclerView.ItemDecoration {
  private var mColorDrawable: ColorDrawable? = null
  private var mHeight: Int = 0
  private var mPaddingLeft: Int = 0
  private var mPaddingRight: Int = 0
  private var mDrawLastItem = true
  private var mDrawHeaderFooter = false

  constructor(color: Int, height: Int) {
    this.mColorDrawable = ColorDrawable(color)
    this.mHeight = height
  }

  constructor(color: Int, height: Int, paddingLeft: Int, paddingRight: Int) {
    this.mColorDrawable = ColorDrawable(color)
    this.mHeight = height
    this.mPaddingLeft = paddingLeft
    this.mPaddingRight = paddingRight
  }

  fun setDrawLastItem(mDrawLastItem: Boolean) {
    this.mDrawLastItem = mDrawLastItem
  }

  fun setDrawHeaderFooter(mDrawHeaderFooter: Boolean) {
    this.mDrawHeaderFooter = mDrawHeaderFooter
  }

  override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
    val position = parent.getChildAdapterPosition(view)
    var orientation = 0
    var headerCount = 0
    var footerCount = 0
    if (parent.adapter is BaseQuickAdapter<*, *>) {
      headerCount = (parent.adapter as BaseQuickAdapter<*, *>).headerLayoutCount
      footerCount = (parent.adapter as BaseQuickAdapter<*, *>).footerLayoutCount
    }

    val layoutManager = parent.layoutManager
    if (layoutManager is androidx.recyclerview.widget.StaggeredGridLayoutManager) {
      orientation = layoutManager.orientation
    } else if (layoutManager is androidx.recyclerview.widget.GridLayoutManager) {
      orientation = layoutManager.orientation
    } else if (layoutManager is androidx.recyclerview.widget.LinearLayoutManager) {
      orientation = layoutManager.orientation
    }

    if (position >= headerCount && position < parent.adapter!!.itemCount - footerCount || mDrawHeaderFooter) {
      if (orientation == androidx.recyclerview.widget.OrientationHelper.VERTICAL) {
        outRect.bottom = mHeight
      } else {
        outRect.right = mHeight
      }
    }
  }

  override fun onDraw(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {

    if (parent.adapter == null) {
      return
    }

    var orientation = 0
    var headerCount = 0
    var footerCount = 0
    val dataCount: Int

    if (parent.adapter is BaseQuickAdapter<*, *>) {
      headerCount = (parent.adapter as BaseQuickAdapter<*, *>).headerLayoutCount
      footerCount = (parent.adapter as BaseQuickAdapter<*, *>).footerLayoutCount
      dataCount = (parent.adapter as BaseQuickAdapter<*, *>).itemCount
    } else {
      dataCount = parent.adapter!!.itemCount
    }
    val dataStartPosition = headerCount
    val dataEndPosition = headerCount + dataCount


    val layoutManager = parent.layoutManager
    if (layoutManager is androidx.recyclerview.widget.StaggeredGridLayoutManager) {
      orientation = layoutManager.orientation
    } else if (layoutManager is androidx.recyclerview.widget.GridLayoutManager) {
      orientation = layoutManager.orientation
    } else if (layoutManager is androidx.recyclerview.widget.LinearLayoutManager) {
      orientation = layoutManager.orientation
    }
    val start: Int
    val end: Int
    if (orientation == androidx.recyclerview.widget.OrientationHelper.VERTICAL) {
      start = parent.paddingLeft + mPaddingLeft
      end = parent.width - parent.paddingRight - mPaddingRight
    } else {
      start = parent.paddingTop + mPaddingLeft
      end = parent.height - parent.paddingBottom - mPaddingRight
    }

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child = parent.getChildAt(i)
      val position = parent.getChildAdapterPosition(child)

      if (position >= dataStartPosition && position < dataEndPosition - 1//数据项除了最后一项

        || position == dataEndPosition - 1 && mDrawLastItem//数据项最后一项

        || !(position >= dataStartPosition && position < dataEndPosition) && mDrawHeaderFooter//header&footer且可绘制
      ) {

        if (orientation == androidx.recyclerview.widget.OrientationHelper.VERTICAL) {
          val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
          val top = child.bottom + params.bottomMargin
          val bottom = top + mHeight
          mColorDrawable!!.setBounds(start, top, end, bottom)
          mColorDrawable!!.draw(c)
        } else {
          val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
          val left = child.right + params.rightMargin
          val right = left + mHeight
          mColorDrawable!!.setBounds(left, start, right, end)
          mColorDrawable!!.draw(c)
        }
      }
    }
  }
}