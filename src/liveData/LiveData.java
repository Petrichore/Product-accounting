package liveData;

public class LiveData<T> {

    private T value;

    private Observable<T> observable;

    public LiveData() {
    }

    public void subscribe(Observable<T> observable) {
        this.observable = observable;
        if (value != null) {
            observable.onChanged(value);
        }
    }

    public void setValue(T value) {
        this.value = value;
        if (observable != null) {
            observable.onChanged(value);
        }

    }

    public T getValue() {
        return value;
    }
}
