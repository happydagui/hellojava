public class Foo {
	public Foo() {
		System.out.println("constructor");
	}
	{
		System.out.println("instance intial");
	}
	static {
		System.out.println("static intial");
	}
	public static void main(String[] args) {
		new Foo();
		new Foo();
		new Foo();
	}
}
