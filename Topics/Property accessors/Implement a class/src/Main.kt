class LewisCarrollBook(
    name: String = "",
    author: String = "Lewis Carroll",
    price: Int = 0) {

    var name = name
        get() {
            println("The name of the book is $field")
            return field
        }
        set(value) {
            println("Now, a book called $value")
            field = value
        }

    var author = author
        get() {
            println("The author of the book is $field")
            return field
        }

    var price = price
        get() {
            println("Putting a new price...")
            return field
        }
        set(value) {
            println("The new price is $value")
            field = value
        }
}
