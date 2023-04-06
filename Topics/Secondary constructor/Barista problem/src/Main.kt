class EspressoMachine(val costPerServing: Float) {
    constructor(coffeeCapsulesCount: Int, totalCost: Float) : this(totalCost / coffeeCapsulesCount)
    constructor(coffeeBeansWeight: Float, totalCost: Float)
        : this(totalCost / coffeeBeansWeight * 10)
}