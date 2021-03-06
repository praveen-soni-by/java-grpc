// @generated by protobuf-ts 1.0.13
// @generated from protobuf file "todo-service.proto" (syntax proto3)
// tslint:disable
import { RpcTransport } from "@protobuf-ts/runtime-rpc";
import { MethodInfo } from "@protobuf-ts/runtime-rpc";
import { WireType } from "@protobuf-ts/runtime";
import { BinaryWriteOptions } from "@protobuf-ts/runtime";
import { IBinaryWriter } from "@protobuf-ts/runtime";
import { UnknownFieldHandler } from "@protobuf-ts/runtime";
import { BinaryReadOptions } from "@protobuf-ts/runtime";
import { IBinaryReader } from "@protobuf-ts/runtime";
import { PartialMessage } from "@protobuf-ts/runtime";
import { reflectionMergePartial } from "@protobuf-ts/runtime";
import { MessageType } from "@protobuf-ts/runtime";
import { stackIntercept } from "@protobuf-ts/runtime-rpc";
import { UnaryCall } from "@protobuf-ts/runtime-rpc";
import { RpcOptions } from "@protobuf-ts/runtime-rpc";
/**
 * @generated from protobuf message EmptyRequest
 */
export interface EmptyRequest {
}
/**
 * @generated from protobuf message Todos
 */
export interface Todos {
    /**
     * @generated from protobuf field: repeated Todo todo = 1;
     */
    todo: Todo[];
}
/**
 * @generated from protobuf message Todo
 */
export interface Todo {
    /**
     * @generated from protobuf field: string textValue = 2;
     */
    textValue: string;
}
/**
 * @generated from protobuf service TodoService
 */
export interface ITodoServiceClient {
    /**
     * @generated from protobuf rpc: getTodos(EmptyRequest) returns (Todos);
     */
    getTodos(input: EmptyRequest, options?: RpcOptions): UnaryCall<EmptyRequest, Todos>;
    /**
     * @generated from protobuf rpc: addTodo(Todo) returns (Todo);
     */
    addTodo(input: Todo, options?: RpcOptions): UnaryCall<Todo, Todo>;
}
/**
 * Type for protobuf message EmptyRequest
 */
class EmptyRequest$Type extends MessageType<EmptyRequest> {
    constructor() {
        super("EmptyRequest", []);
    }
    create(value?: PartialMessage<EmptyRequest>): EmptyRequest {
        const message = {};
        if (value !== undefined)
            reflectionMergePartial<EmptyRequest>(this, message, value);
        return message;
    }
    internalBinaryRead(reader: IBinaryReader, length: number, options: BinaryReadOptions, target?: EmptyRequest): EmptyRequest {
        return target ?? this.create();
    }
    internalBinaryWrite(message: EmptyRequest, writer: IBinaryWriter, options: BinaryWriteOptions): IBinaryWriter {
        let u = options.writeUnknownFields;
        if (u !== false)
            (u == true ? UnknownFieldHandler.onWrite : u)(this.typeName, message, writer);
        return writer;
    }
}
export const EmptyRequest = new EmptyRequest$Type();
/**
 * Type for protobuf message Todos
 */
class Todos$Type extends MessageType<Todos> {
    constructor() {
        super("Todos", [
            { no: 1, name: "todo", kind: "message", repeat: 1 /*RepeatType.PACKED*/, T: () => Todo }
        ]);
    }
    create(value?: PartialMessage<Todos>): Todos {
        const message = { todo: [] };
        if (value !== undefined)
            reflectionMergePartial<Todos>(this, message, value);
        return message;
    }
    internalBinaryRead(reader: IBinaryReader, length: number, options: BinaryReadOptions, target?: Todos): Todos {
        let message = target ?? this.create(), end = reader.pos + length;
        while (reader.pos < end) {
            let [fieldNo, wireType] = reader.tag();
            switch (fieldNo) {
                case /* repeated Todo todo */ 1:
                    message.todo.push(Todo.internalBinaryRead(reader, reader.uint32(), options));
                    break;
                default:
                    let u = options.readUnknownField;
                    if (u === "throw")
                        throw new globalThis.Error(`Unknown field ${fieldNo} (wire type ${wireType}) for ${this.typeName}`);
                    let d = reader.skip(wireType);
                    if (u !== false)
                        (u === true ? UnknownFieldHandler.onRead : u)(this.typeName, message, fieldNo, wireType, d);
            }
        }
        return message;
    }
    internalBinaryWrite(message: Todos, writer: IBinaryWriter, options: BinaryWriteOptions): IBinaryWriter {
        /* repeated Todo todo = 1; */
        for (let i = 0; i < message.todo.length; i++)
            Todo.internalBinaryWrite(message.todo[i], writer.tag(1, WireType.LengthDelimited).fork(), options).join();
        let u = options.writeUnknownFields;
        if (u !== false)
            (u == true ? UnknownFieldHandler.onWrite : u)(this.typeName, message, writer);
        return writer;
    }
}
export const Todos = new Todos$Type();
/**
 * Type for protobuf message Todo
 */
class Todo$Type extends MessageType<Todo> {
    constructor() {
        super("Todo", [
            { no: 2, name: "textValue", kind: "scalar", T: 9 /*ScalarType.STRING*/ }
        ]);
    }
    create(value?: PartialMessage<Todo>): Todo {
        const message = { textValue: "" };
        if (value !== undefined)
            reflectionMergePartial<Todo>(this, message, value);
        return message;
    }
    internalBinaryRead(reader: IBinaryReader, length: number, options: BinaryReadOptions, target?: Todo): Todo {
        let message = target ?? this.create(), end = reader.pos + length;
        while (reader.pos < end) {
            let [fieldNo, wireType] = reader.tag();
            switch (fieldNo) {
                case /* string textValue */ 2:
                    message.textValue = reader.string();
                    break;
                default:
                    let u = options.readUnknownField;
                    if (u === "throw")
                        throw new globalThis.Error(`Unknown field ${fieldNo} (wire type ${wireType}) for ${this.typeName}`);
                    let d = reader.skip(wireType);
                    if (u !== false)
                        (u === true ? UnknownFieldHandler.onRead : u)(this.typeName, message, fieldNo, wireType, d);
            }
        }
        return message;
    }
    internalBinaryWrite(message: Todo, writer: IBinaryWriter, options: BinaryWriteOptions): IBinaryWriter {
        /* string textValue = 2; */
        if (message.textValue !== "")
            writer.tag(2, WireType.LengthDelimited).string(message.textValue);
        let u = options.writeUnknownFields;
        if (u !== false)
            (u == true ? UnknownFieldHandler.onWrite : u)(this.typeName, message, writer);
        return writer;
    }
}
export const Todo = new Todo$Type();
/**
 * @generated from protobuf service TodoService
 */
export class TodoServiceClient implements ITodoServiceClient {
    readonly typeName = "TodoService";
    readonly methods: MethodInfo[] = [
        { service: this, name: "getTodos", I: EmptyRequest, O: Todos },
        { service: this, name: "addTodo", I: Todo, O: Todo }
    ];
    constructor(private readonly _transport: RpcTransport) {
    }
    getTodos(input: EmptyRequest, options?: RpcOptions): UnaryCall<EmptyRequest, Todos> {
        const method = this.methods[0], opt = this._transport.mergeOptions(options), i = method.I.create(input);
        return stackIntercept<EmptyRequest, Todos>("unary", this._transport, method, opt, i);
    }
    addTodo(input: Todo, options?: RpcOptions): UnaryCall<Todo, Todo> {
        const method = this.methods[1], opt = this._transport.mergeOptions(options), i = method.I.create(input);
        return stackIntercept<Todo, Todo>("unary", this._transport, method, opt, i);
    }
}
