package com.area_b.samples

import org.springframework.dao.DataIntegrityViolationException

class TodoController {

    static allowedMethods = [create: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "read")
    }

    def read() {
        render(view: "/todo/index", model: [tasks: Todo.list()])
    }

    def create() {
        def todo = new Todo(params)
        if (!todo.hasErrors()) {
            todo.save()
        }
        redirect(action: "read")
    }

    def update(Long id) {
        def todoListInstance = Todo.get(id)
        if (!todoListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'todoList.label', default: 'Todo'), id])
            redirect(action: "read")
            return
        }

        todoListInstance.properties = params
        if (!todoListInstance.save(flush: true)) {
            render(view: "edit", model: [todoListInstance: todoListInstance])
            return
        }

        redirect(action: "read")
    }

    def delete(Long id) {
        def todoInstance = Todo.get(id)
        if (!todoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'todoList.label', default: 'Todo'), id])
            redirect(action: "read")
            return
        }

        try {
            todoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'todoList.label', default: 'Todo'), id])
            redirect(action: "read")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'todoList.label', default: 'Todo'), id])
            redirect(action: "read")
        }
    }
}
