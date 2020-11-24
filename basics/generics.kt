class MutableStack<T>(vararg items : T) {

    private val elements = items.toMutableList();

    fun push(element: T) = elements.add(element);

    fun peek() = elements.last();

    fun pop() = elements.removeAt(elements.size - 1);

    fun isEmpty() = elements.isEmpty();

    fun size() = elements.size;

    override fun toString() = "MutableStack(${elements.joinToString()});"

}

fun main() {
    val stack = MutableStack(6.12, 0.98, 5.0);
    println("size: " + stack.size());
    println("peek: " + stack.peek());
    println("push: " + stack.push(96.4));
    println("size: " + stack.size());
    println("peek: " + stack.peek());
    println("pop: " + stack.pop());
    println("size: " + stack.size());
    println("peek: " + stack.peek());
    println(stack);
}