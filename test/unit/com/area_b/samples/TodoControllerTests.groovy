package com.area_b.samples

import grails.test.mixin.*

@TestFor(TodoController)
@Mock(Todo)
class TodoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/todoList/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.todoListInstanceList.size() == 0
        assert model.todoListInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.todoListInstance != null
    }

    void testSave() {
        controller.save()

        assert model.todoListInstance != null
        assert view == '/todoList/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/todoList/show/1'
        assert controller.flash.message != null
        assert Todo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/todoList/list'

        populateValidParams(params)
        def todoList = new Todo(params)

        assert todoList.save() != null

        params.id = todoList.id

        def model = controller.show()

        assert model.todoListInstance == todoList
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/todoList/list'

        populateValidParams(params)
        def todoList = new Todo(params)

        assert todoList.save() != null

        params.id = todoList.id

        def model = controller.edit()

        assert model.todoListInstance == todoList
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/todoList/list'

        response.reset()

        populateValidParams(params)
        def todoList = new Todo(params)

        assert todoList.save() != null

        // test invalid parameters in update
        params.id = todoList.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/todoList/edit"
        assert model.todoListInstance != null

        todoList.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/todoList/show/$todoList.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        todoList.clearErrors()

        populateValidParams(params)
        params.id = todoList.id
        params.version = -1
        controller.update()

        assert view == "/todoList/edit"
        assert model.todoListInstance != null
        assert model.todoListInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/todoList/list'

        response.reset()

        populateValidParams(params)
        def todoList = new Todo(params)

        assert todoList.save() != null
        assert Todo.count() == 1

        params.id = todoList.id

        controller.delete()

        assert Todo.count() == 0
        assert Todo.get(todoList.id) == null
        assert response.redirectedUrl == '/todoList/list'
    }
}
