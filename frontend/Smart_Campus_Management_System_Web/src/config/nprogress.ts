import NProgress from "nprogress"
import "nprogress/nprogress.css"
NProgress.configure({
    easing: 'ease',  // animate
    speed: 500,  // incrementing the speed of the progress bar
    showSpinner: false,  // whether to display the loading icon
    trickleSpeed: 200,  // auto increment interval
    minimum: 0.3 // minimum percentage at initialization
})
export default NProgress