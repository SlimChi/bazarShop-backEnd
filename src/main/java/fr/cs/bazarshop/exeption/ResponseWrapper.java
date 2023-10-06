package fr.cs.bazarshop.exeption;

public class ResponseWrapper<T> {
    private T data;
    private CustomError error;

    public ResponseWrapper(T data) {
        this.data = data;
    }

    public ResponseWrapper(CustomError error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CustomError getError() {
        return error;
    }

    public void setError(CustomError error) {
        this.error = error;
    }
}
