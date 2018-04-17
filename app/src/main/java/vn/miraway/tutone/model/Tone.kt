package vn.miraway.tutone.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

open class Tone(var id: String, var name: String,var category: String, var description: String, var url: String) : RealmObject() {

    constructor() : this("","","","","")
}