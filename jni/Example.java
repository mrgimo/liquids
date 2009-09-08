class Example {

    public native void print();

    static {
        System.loadLibrary("example");
    }

    public static void main(String[] args){
	new Example().print();
    }
}

