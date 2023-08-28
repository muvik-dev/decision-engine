<template>
  <div id="app">
    <el-row>
      <el-col :span="6">
        <el-form :model="form" :rules="formRules" ref="ruleForm" label-width="120px" >
          <el-form-item label="Personal Code:" prop="personalCode">
            <el-input v-model="form.personalCode"></el-input>
          </el-form-item>
          <el-form-item label="Loan Amount:" prop="loanAmount">
            <el-input-number v-model="form.loanAmount" controls-position="right" :min="2000" :max="10000" @change="clearResult()"></el-input-number>
          </el-form-item>
          <el-form-item label="Loan Period (months):" prop="loanPeriod">
            <el-input-number v-model="form.loanPeriod" controls-position="right" :min="12" :max="60" @change="clearResult()"></el-input-number>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="submitForm('ruleForm')">Submit</el-button>
      </el-col>
    </el-row>
    <div v-if="result.decision">
      <el-tag :type="result.decision === 'POSITIVE' ? 'success' : 'danger'" class="credit-tag">{{result.decision }}</el-tag>
      <el-tag class="credit-tag">Approved Amount: {{ result.approvedAmount }}</el-tag>
    </div>
    <div v-if="result.decision && form.loanPeriod !== result.loanPeriod">
      <el-tag type="info">But, you can take loan of {{ form.loanAmount }} if you choose a period of {{ result.loanPeriod }} months</el-tag>
    </div>
  </div>
</template>

<script>
import { getDecision } from "@/api/credit"
export default {
  name: 'App',
  data() {
    return {
      form: {
        personalCode: '',
        loanAmount: 2000,
        loanPeriod: 12
      },
      result: {
        decision: null,
        approvedAmount: null,
        loanPeriod: null
      },
      formRules: {
        personalCode: [
          { required: true, message: 'Please input Personal code', trigger: 'blur', min: 1 }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          getDecision(this.form).then((res) => {
            this.result.decision = res.data.decision;
            this.result.approvedAmount = res.data.approvedAmount;
            this.result.loanPeriod = res.data.loanPeriod;
          }).catch(() => {
            this.$message.error('Error')
          })
        } else {
          this.$message.error('Fill all the fields')
          return false;
        }
      });
    },
    clearResult() {
      this.result = {
        decision: null,
        approvedAmount: null,
        loanPeriod: null
      }
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.credit-tag {
  margin: 10px;
}
</style>
