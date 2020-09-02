require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |spec|
  spec.name = "voxeet-uxkit-reactnative"
  spec.version = package["version"]
  spec.summary = "The Voxeet UXKit is a quick way of adding premium audio, video chats, and other supported options."
  spec.license = "MIT"
  spec.author = "Voxeet"
  spec.homepage = "https://dolby.io"
  spec.platform = :ios, "11.0"
  spec.swift_version = "5.2.4"
  spec.source = { :git => "https://github.com/voxeet/voxeet-uxkit-reactnative.git", :tag => "v#{spec.version}" }
  spec.source_files  = "ios/**/*.{h,m}"
  spec.framework = "UIKit"
  spec.dependency "React"
  spec.dependency "VoxeetUXKit", "1.3.4"
  spec.dependency "VoxeetSDK", "2.4.0"
  spec.source = { :http => "https://vox-ios-sdk.s3.us-east-1.amazonaws.com/sdk/ios/release/#{spec.version}/VoxeetSDK.zip" }
end
