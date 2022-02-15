<template>
    <div class="app-container">
        <div class="filter-container">
            <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit"
                @click="handleCreate">
                添加
            </el-button>
        </div>
        
        <el-table :data="list" style="width: 100%;margin-bottom: 20px;" row-key="id" border
            :tree-props="{children: 'children', hasChildren: 'hasChildren'}">

            <el-table-column prop="name" label="名称" width="180">
            </el-table-column>
            <el-table-column prop="perms" label="权限标识">
            </el-table-column>
            <el-table-column prop="type" label="类型" width="180">
            </el-table-column>
        </el-table>

        <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
            <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="110px"
                style="width: 400px; margin-left:50px;">
                <el-form-item>
                    <div>
                        <div class="mask" v-show="isShowSelect"></div>
                        <el-popover placement="bottom-start" :width="popoverWidth" trigger="manual" v-model="isShowSelect"
                            @hide="popoverHide">
                            <el-tree class="common-tree" :width="width" ref="tree" :data="node" :props="obj"
                                :show-checkbox="multiple" :node-key="obj.id" :check-strictly="checkStrictly"
                                 :expand-on-click-node="multiple&&expandClickNode"
                                 :highlight-current="true" @check-change="nodeClick"
                                @node-click="nodeClick"></el-tree>
                            <el-select slot="reference" ref="select" :size="size" v-model="returnDataKeys" :multiple="multiple"
                                 :collapse-tags="collapseTags" @click.native="selectClick"
                                 @clear="clean" class="tree-select">
                                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                                </el-option>
                            </el-select>
                            <el-row>
                                <el-button v-if="multiple" class="ok" @click="isShowSelect=false" size="mini" type="text">确定
                                </el-button>
                            </el-row>
                        </el-popover>
                    </div>
                </el-form-item>
                <el-form-item label="菜单名称" prop="name">
                    <el-input v-model="temp.name" />
                </el-form-item>
                <el-form-item label="页面路径" prop="url">
                    <el-input v-model="temp.url" />
                </el-form-item>
                <el-form-item label="菜单类型" prop="type">
                    <el-select v-model="temp.type" class="filter-item" placeholder="Please select">
                        <el-option v-for="item in typeStatus" :key="item" :label="item" :value="item" />
                    </el-select>
                </el-form-item>
                <el-form-item label="权限标识" prop="perms">
                    <el-input v-model="temp.perms" />
                </el-form-item>
                <el-form-item label="序号" prop="orderNum">
                    <el-input v-model="temp.orderNum" />
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">
                    取消
                </el-button>
                <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
                    提交
                </el-button>
            </div>
        </el-dialog>


    </div>
</template>

<script>

    import { getMenuNav, getMenuNode, saveMenu} from '@/api/menu'
    export default {
        name: 'ComplexTable',
        props: { 
            obj: {
                type: Object,
                required: false,
                default: () => {
                    return {
                        id: 'id',// ID
                        label: 'name',// 显示名称
                        children: 'children', //子级字段名
                        path: 'path',//路径
                        content: 'content',//描述
                        pid: 'pid',//父id
                    }
                }
            },
            // 配置是否可多选
            multiple: {
                type: Boolean,
                default() {
                    return false;
                }
            },
            // 配置是否可清空选择
            clearable: {
                type: Boolean,
                default() {
                    return true;
                }
            },
            // 配置多选时是否将选中值按文字的形式展示
            collapseTags: {
                type: Boolean,
                default() {
                    return false;
                }
            },
            // 显示复选框情况下，是否严格遵循父子不互相关联
            checkStrictly: {
                type: Boolean,
                default() {
                    return false;
                }
            }, 
            // 默认选中的节点key
            defaultKey: {
                type: [Number, String, Array, Object],
                default() {
                    return [];
                }
            },
            size: {
                type: String,
                default() {
                    return 'small';
                }
            },
            width: {
                type: String,
                default() {
                    return '100%';
                }
            },
            height: {
                type: String,
                default() {
                    return '300px';
                }
            }
        },
        data() {
            return {
                list: [],
                node:[],
                listLoading: true,
                temp: {
                    id: "",
                    parentId: '',
                    name: '',
                    url: '',
                    perms: "",
                    type: "",
                    orderNum: ""
                },
                typeStatus: [0, 1, 2],
                dialogFormVisible: false,
                dialogStatus: '',
                textMap: {
                    update: 'Edit',
                    create: 'Create'
                },
                dialogPvVisible: false,
                rules: {
                    parentId: [{ required: true, message: 'parentId is required', trigger: 'change' }],
                    name: [{ required: true, message: 'name is required', trigger: 'change' }],
                    type: [{ required: true, message: 'type is required', trigger: 'change' }],
                    orderNum: [{ required: true, message: 'orderNum is required', trigger: 'change' }]
                },
                downloadLoading: false,
                popoverWidth: "0px",//下拉框大小
                isShowSelect: false, // 是否显示树状选择器
                options: [],//select option选项
                returnDatas: [],//返回给父组件数组对象
                returnDataKeys: [],//返回父组件数组主键值
            }
        },
        created() {
            this.getList()
        },
        methods: {
            getList() {
                this.listLoading = true
                getMenuNav().then(response => {
                    this.list = response.data 
                    // Just to simulate the time of the request
                    setTimeout(() => {
                        this.listLoading = false
                    }, 1.5 * 1000)
                })
            },
            getNodeList() {
                this.listLoading = true 
                getMenuNode().then(response => {
                    this.node = response.data 
                    // Just to simulate the time of the request
                    setTimeout(() => {
                        this.listLoading = false
                    }, 1.5 * 1000)
                })
            },
            resetTemp() {
                this.temp = {
                    id: "",
                    parentId: '',
                    name: '',
                    url: '',
                    perms: "",
                    type: "",
                    orderNum: ""
                }
            },
            handleCreate() {
                this.resetTemp()
                this.dialogStatus = 'create'
                this.dialogFormVisible = true 
                this.getNodeList();
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
                })
            },
            createData() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) { 
                        saveMenu(this.temp).then(() => {
                            this.list.unshift(this.temp)
                            this.dialogFormVisible = false
                            this.$notify({
                                title: 'Success',
                                message: 'Created Successfully',
                                type: 'success',
                                duration: 2000
                            })
                        })
                    }
                })
            },
            init() {
                // eslint-disable-next-line no-undef,no-debugger
                // debugger
                if (this.defaultKey != undefined && this.defaultKey.length > 0) {
                    if (this.multiple) {
                        // 多选
                        if (Object.prototype.toString.call(this.defaultKey).indexOf("Array") != -1) {
                            if (Object.prototype.toString.call(this.defaultKey[0]).indexOf("Object") != -1) {//对象
                                this.setDatas(this.defaultKey);
                            } else if (Object.prototype.toString.call(this.defaultKey[0]).indexOf("Number") != -1
                                || Object.prototype.toString.call(this.defaultKey[0]).indexOf("String") != -1) {
                                this.setKeys(this.defaultKey);
                            } else {
                                console.log("多选：传入参数类型不匹配");
                                return;
                            }
                        } else {
                            console.log("多选：传入参数类型不匹配");
                            return;
                        }

                    } else {
                        // 单选
                        if (Object.prototype.toString.call(this.defaultKey).indexOf("Number") != -1
                            || Object.prototype.toString.call(this.defaultKey).indexOf("String") != -1
                            || Object.prototype.toString.call(this.defaultKey).indexOf("Object") != -1) {
                            this.setKey(this.defaultKey);
                        } else {
                            console.log("单选：传入参数类型不匹配");
                            return;

                        }

                    }
                }
            },
            //下拉框select点击[入口]
            selectClick() {
                this.$nextTick(function () {//设置下拉框自适应宽度
                    this.popoverWidth = this.$refs.select.$el.clientWidth - 26;
                })
                //显示下拉框
                return this.isShowSelect = !this.isShowSelect
            },
            //单选: 树点击方法
            nodeClick(data, node) {
                if (!this.multiple) {//单选
                    this.isShowSelect = false;
                    this.setKey(node.key);
                } else {//多选
                    var checkedKeys = this.$refs.tree.getCheckedKeys(); // 所有被选中的节点的 key 所组成的数组数据
                    var t = [];
                    this.options = checkedKeys.map((item) => {//设置option选项
                        var node = this.$refs.tree.getNode(item); // 所有被选中的节点对应的node
                        t.push(node.data);
                        return { label: node.label, value: node.key };
                    });
                    this.returnDataKeys = this.options.map((item) => {
                        return item.value;
                    });
                    this.returnDatas = t;
                }
            },
            //单选:清空选中
            clean() {
                this.$refs.tree.setCurrentKey(null);//清除树选中key
                this.returnDatas = null; this.returnDataKeys = '';
                this.popoverHide();

            },
            //单选:设置、初始化值 key
            setKey(thisKey) {
                this.$refs.tree.setCurrentKey(thisKey);
                var node = this.$refs.tree.getNode(thisKey);
                this.setData(node.data);
            },
            //单选：设置、初始化对象
            setData(data) {
                this.options = [];
                this.options.push({ label: data[this.obj.label], value: data[this.obj.id] });
                this.returnDatas = data;
                this.returnDataKeys = data[this.obj.id]

            },
            //下拉框关闭执行
            popoverHide() {
                this.$emit('getValue', this.returnDataKeys, this.returnDatas);
            },
            // 多选，清空所有勾选
            clearSelectedNodes() {
                var checkedKeys = this.$refs.tree.getCheckedKeys(); // 所有被选中的节点的 key 所组成的数组数据
                for (let i = 0; i < checkedKeys.length; i++) {
                    this.$refs.tree.setChecked(checkedKeys[i], false);
                }
            },
 
            switchTree() {
                return this.buildTree(this.data, this.defaultValue);
            }, 

        },
        watch: {
            // eslint-disable-next-line no-unused-vars
            isShowSelect(val) {
                // 隐藏select自带的下拉框
                this.$refs.select.blur();
            }, 
        }


    }
</script>