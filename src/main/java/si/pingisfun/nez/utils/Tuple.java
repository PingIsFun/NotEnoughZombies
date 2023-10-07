package si.pingisfun.nez.utils;

class Tuple {
    private final Object a;
    private final Object b;

    public Tuple(Object a, Object result) {
        this.a = a;
        this.b = result;
    }

    public Object getA() {
        return a;
    }

    public Object getB() {
        return b;
    }

    @Override
    public String toString() {
        return "Tuple("+ a + "," + b + ")";
    }
}

