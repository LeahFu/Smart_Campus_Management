import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import {createSvgIconsPlugin} from 'vite-plugin-svg-icons'
import path from 'path'

function resolve (dir){
  return path.join(__dirname, '.', dir)
}
// https://vitejs.dev/config/
export default defineConfig({
  envDir: "./viteenv",  //relative path
  plugins: [vue(),
    //Use svg icon
    createSvgIconsPlugin({
      //Specify the icon folder that needs to be cached
      iconDirs:[path.resolve(process.cwd(),'src/icons/svg')],
      //Specify the symbolId format
      symbolId:'icon-[dir]-[name]'
    })
  ],
})
