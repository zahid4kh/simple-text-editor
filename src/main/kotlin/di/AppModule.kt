package di

import org.koin.dsl.module
import viewmodel.MainViewModel

val appModule = module {
    single { MainViewModel() }
}