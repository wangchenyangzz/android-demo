package cn.yy.demo.dagger.module

import cn.yy.demo.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject


/**
 *    author : cy.wang
 *    date   : 2020/7/13
 *    desc   :
 */
@Module
class CarModule {
    @Provides
    fun getCar(): Car {
        return Car()
    }
}

@Module
class CarTypeModule {
    @Provides
    fun getCarType(): CarType {
        return CarType()
    }
}

class Car {
    @Inject
    lateinit var type : CarType

    init {
        DaggerCarTypeComponent.create().inject(this)
    }

    override fun toString(): String {
        return super.toString() + type.toString()
    }
}

class CarType {
    override fun toString(): String {
        return "car type"
    }
}

@Component(modules = [CarModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity?)
}

@Component(modules = [CarTypeModule::class])
interface CarTypeComponent {
    fun inject(car: Car?)
}