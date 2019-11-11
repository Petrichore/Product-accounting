package liveData;

public interface Observable<T> {
    void onChanged(T t);
}
