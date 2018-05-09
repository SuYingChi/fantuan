package com.wetime.fanc.customview;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.shizhefei.view.largeimage.ILargeImageView;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;

import java.io.File;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * A base {@link com.bumptech.glide.request.target.Target} for displaying resources in
 * {@link android.widget.ImageView}s.
 *
 * @param <Z> The type of resource that this target will display in the wrapped {@link android.widget.ImageView}.
 */
public class LargeImageViewTarget extends ViewTarget<View, File>{
    private ILargeImageView largeImageView;
    public <V extends View & ILargeImageView> LargeImageViewTarget(V view) {
        super(view);
        this.largeImageView = view;
    }

    /**
     * Sets the given {@link android.graphics.drawable.Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(android.graphics.drawable.Drawable)}.
     *
     * @param placeholder {@inheritDoc}
     */
    @Override
    public void onLoadStarted(Drawable placeholder) {
        super.onLoadStarted(placeholder);
        largeImageView.setImageDrawable(placeholder);
    }



    /**
     * The method that will be called when the resource load has finished.
     *
     * @param resource   the loaded resource.
     * @param transition
     */
    @Override
    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
        largeImageView.setImage(new FileBitmapDecoderFactory(resource));
    }


    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        if(errorDrawable!=null){
            largeImageView.setImageDrawable(errorDrawable);
        }
    }

    /**
     * Sets the given {@link android.graphics.drawable.Drawable} on the view using
     * {@link android.widget.ImageView#setImageDrawable(android.graphics.drawable.Drawable)}.
     *
     * @param placeholder {@inheritDoc}
     */
    @Override
    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);
        largeImageView.setImageDrawable(placeholder);
    }



}
