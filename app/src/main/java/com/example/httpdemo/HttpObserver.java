package com.example.httpdemo;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author 马山水
 * @date 2018/3/22
 * @desc TODO
 */

public class HttpObserver<T> implements Observer<T>, LoadingDialogHandler.DialogCancleListener {

    //rxjava2新增接口，简单理解为观察者与观察对象的连接相关操作
    private Disposable disposable;

    private LoadingDialogHandler mProgressDialogHandler;
    private OnNextListener onNextListener;
    private OnCompleteListener onCompleteListener;
    private onErrorListener onErrorListener;
    private Context context;

    public HttpObserver(Context context, OnNextListener onNextListener) {
        mProgressDialogHandler = new LoadingDialogHandler(context, this);
        this.onNextListener = onNextListener;
        this.context = context;
    }

    public HttpObserver(OnNextListener onNextListener, OnCompleteListener onCompleteListener, Context context) {
        mProgressDialogHandler = new LoadingDialogHandler(context, this);
        this.onNextListener = onNextListener;
        this.onCompleteListener = onCompleteListener;
        this.context = context;
    }

    public HttpObserver(OnNextListener onNextListener, OnCompleteListener onCompleteListener, HttpObserver.onErrorListener onErrorListener, Context context) {
        mProgressDialogHandler = new LoadingDialogHandler(context, this);
        this.onNextListener = onNextListener;
        this.onCompleteListener = onCompleteListener;
        this.onErrorListener = onErrorListener;
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
        showProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        if (onErrorListener != null) {
            onErrorListener.onError(e);
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        if (onCompleteListener != null) {
            onCompleteListener.onComplete();
        }
    }

    @Override
    public void onNext(T t) {
        onNextListener.onNext(t);
    }

    @Override
    public void onCancle() {
        if (disposable != null) {
            //取消关联，即取消本次请求
            disposable.dispose();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(LoadingDialogHandler.SHOW_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(LoadingDialogHandler.DISMISS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    public interface OnNextListener<T> {
        void onNext(T t);
    }

    public interface OnCompleteListener {
        void onComplete();
    }

    public interface onErrorListener {
        void onError(Throwable throwable);
    }
}
