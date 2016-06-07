package com.example.caios.desafio;

/**
 * Created by caios on 6/7/16.
 */
public interface IInfiniteScrollListener {
    public void endIsNear();

    // Item visibility code
    public void onScrollCalled(int firstVisibleItem, int visibleItemCount, int totalItemCount);
}