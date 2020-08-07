<template>
	<view class="content"> 
		<p>Fvv Uni TTS</p>
		<button class="title" @click="init">初始化</button>
		<button class="title" @click="play">播放</button>
		<button class="title" @click="setEngines">设置引擎</button>
		<button class="title" @click="saveFile">保存语音文件</button>
		<button class="title" @click="setVoice">设置语速音调</button>
	</view>
</template>

<script>
	const FvvUniTTS = uni.requireNativePlugin('Fvv-UniTTS')
	export default {
		data() {
			return {
				
			}
		},
		onLoad() {

		},
		methods: {
			init(){
				FvvUniTTS.init((callback) => {
					console.log(callback)
				});
				
				FvvUniTTS.onStart((res) => {
					console.log("onStart:" + res)
				});
				
				FvvUniTTS.onDone((res) => {
					console.log("onDone:" + res)
				});
				
				FvvUniTTS.onError((res) => {
					console.log("onError:" + res)
				});
			},
			play(){
				FvvUniTTS.speak({
					text:"hello f v v",
					id:1,
				});
			},
			setEngines(){
				let setEngine = "com.iflytek.speechcloud"
				
				//获取已安装的引擎
				FvvUniTTS.getInstallTTS(res => {
					if(res == null || res.length <= 0){
						return
					}
					if(JSON.stringify(res).indexOf(setEngine) < 0){
						console.log("未安装该语音引擎")
						return
					}
					console.log("set engine : " + FvvUniTTS.setEngine(setEngine));
					FvvUniTTS.speak({
						text:"设置成功",
						id:2,
					});
				})
			},
			saveFile(){
				FvvUniTTS.saveAudioFile({
					text:"hello",
					id:3,
					path:"/sdcard/test/1.wav"
				})
			},
			setVoice(){
				FvvUniTTS.setPitch(100)
				FvvUniTTS.setSpeechRate(100)
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	} 
</style>
