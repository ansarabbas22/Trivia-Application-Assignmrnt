package com.rapidzz.tirivia_application_assignment.models.dataSource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.CategoryModel
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.SolvedQuestionModel


@Database(
    entities = arrayOf(CategoryModel::class, SolvedQuestionModel::class),
    version = 1,
    exportSchema = false
)
abstract class TriviaDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun questionAnswersDao(): AnswersDao

    companion object {
        @Volatile
        private var INSTANCE: TriviaDatabase? = null

        fun getDatabase(context: Context): TriviaDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TriviaDatabase::class.java,
                    "trivia_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}